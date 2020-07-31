package com.leyou.user.service;

import com.leyou.common.utils.NumberUtils;
import com.leyou.user.mapper.UserMapper;
import com.leyou.user.pojo.User;
import com.leyou.user.utils.CodecUtils;
import com.rabbitmq.client.AMQP;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "user:verify:";  //不同的前缀来区分

    /**
     * 校验数据是否可用
     * @param data
     * @param type
     * @return
     */
    public Boolean checkUser(String data, Integer type) {
        User record = new User();
        if(type == 1){
            record.setUsername( data );
        }
        else if (type == 2){
            record.setPhone( data );
        }else {
            return null;
        }
        return this.userMapper.selectCount( record ) == 0;
    }

    public void sendVerifyCode(String phone) {
        if(StringUtils.isBlank( phone )) return;
        //生成验证码
        String code = NumberUtils.generateCode( 6 );

        //发送消息到rabbitMQ
        Map<String,String> msg = new HashMap<>(  );
        msg.put( "phone",phone );
        msg.put( "code",code );
        this.amqpTemplate.convertAndSend( "LEYOU.SMS.EXCHANGE","verifycode.sms",msg);
        //把验证码保存到redis中
        this.redisTemplate.opsForValue().set(KEY_PREFIX + phone,code,2, TimeUnit.MINUTES );
    }

    public void register(User user, String code) {
        //查询redis中的验证码
        String redisCode = this.redisTemplate.opsForValue().get( KEY_PREFIX + user.getPhone() );
        //校验验证码
        if(!StringUtils.equals( code, redisCode)) return;
        //生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt( salt );
        //加盐加密
        user.setPassword( CodecUtils.md5Hex( user.getPassword(),salt ) );
        //新增用户
        user.setId( null );
        user.setCreated( new Date(  ) );
        this.userMapper.insertSelective( user );
        if(this.redisTemplate.hasKey(KEY_PREFIX + user.getPhone())){
            this.redisTemplate.delete( KEY_PREFIX + user.getPhone() );
        }
    }

    public User queryUser(String username, String password) {
        User record = new User();
        record.setUsername( username );
        User user = this.userMapper.selectOne( record );
        if(user == null) return null;
        //获取盐，对用户输入的密码进行加盐加密
        password = CodecUtils.md5Hex( password,user.getSalt() );

        if(StringUtils.equals( password,user.getPassword() )){
            return user;
        }else {
            return null;
        }
    }
}

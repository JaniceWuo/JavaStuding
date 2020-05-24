package com.test;


import com.dao.IUserDao;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws IOException {
        //读取配置文件
        //创建SqlSessionFactory工厂
        //使用工厂生产SqlSession对象
        //使用SqlSession创建Dao接口的代理对象
        //使用代理对象执行方法
        //释放资源

        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);

        SqlSession session = factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);

        List<User> users = userDao.findAll();

        for(User user:users){
            System.out.println(user);
        }
        session.close();
        is.close();
    }
}

package com.leyou.cart.service;

import com.leyou.cart.client.GoodsClient;
import com.leyou.cart.interceptor.LoginInterceptor;
import com.leyou.cart.pojo.Cart;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.JsonUtils;
import com.leyou.item.pojo.Sku;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private GoodsClient goodsClient;

    private static final String KEY_PREFIX = "user:cart:";

    RedisSerializer stringSerializer = new StringRedisSerializer();


    public void addCart(Cart cart) {
//        System.out.println("执行了cart方法");
        redisTemplate.setKeySerializer( stringSerializer );
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        //获取用户信息
        UserInfo userInfo = LoginInterceptor.getUserInfo();
        //查询购物车记录
        BoundHashOperations<String,Object,Object> boundHashOperations = this.redisTemplate.boundHashOps( KEY_PREFIX + userInfo.getId() );
        String key = cart.getSkuId().toString();
        Integer num = cart.getNum();

        //判断当前商品是否已加入过购物车
        if(boundHashOperations.hasKey( key )){
            String cartJson = boundHashOperations.get( key ).toString();
            cart = JsonUtils.parse( cartJson, Cart.class );
            cart.setNum( cart.getNum() + num );
        }else {
            Sku sku = this.goodsClient.querySkuBySkuId( cart.getSkuId() );
            cart.setUserId( userInfo.getId() );
            cart.setTitle( sku.getTitle() );
            cart.setOwnSpec( sku.getOwnSpec() );
            cart.setImage( StringUtils.isBlank(sku.getImages())?"": StringUtils.split( sku.getImages() ,",")[0] );
            cart.setPrice( sku.getPrice() );
        }
        boundHashOperations.put( key,JsonUtils.serialize( cart) );
    }

    public List<Cart> queryCarts() {
        UserInfo userInfo = LoginInterceptor.getUserInfo();
        if(!this.redisTemplate.hasKey( KEY_PREFIX + userInfo.getId() )){
            return null;
        }
        //获取用户的购物车记录
        BoundHashOperations hashOperations = this.redisTemplate.boundHashOps( KEY_PREFIX + userInfo.getId() );
        List<Object> cartsJson = hashOperations.values();

        //如果购物车集合为空 直接返回
        if(CollectionUtils.isEmpty( cartsJson )){
            return null;
        }
        return cartsJson.stream().map(cartJson -> JsonUtils.parse( cartJson.toString(),Cart.class
        )).collect( Collectors.toList() );
    }

    public void updateNum(Cart cart) {
        UserInfo userInfo = LoginInterceptor.getUserInfo();
        if(!this.redisTemplate.hasKey( KEY_PREFIX + userInfo.getId() )){
            return;
        }
        Integer num = cart.getNum();
        BoundHashOperations hashOperations = this.redisTemplate.boundHashOps( KEY_PREFIX + userInfo.getId() );
        String cartJson = hashOperations.get( cart.getSkuId().toString() ).toString();
        cart = JsonUtils.parse(cartJson,Cart.class);
        cart.setNum( num );
        hashOperations.put( cart.getSkuId().toString(),JsonUtils.serialize( cart ) );
    }

    public void deleteCart(String skuId) {
        UserInfo userInfo = LoginInterceptor.getUserInfo();
        String key = KEY_PREFIX + userInfo.getId();
        BoundHashOperations hashOps = this.redisTemplate.boundHashOps( key );
//        hashOps.entries().forEach((m,n) -> System.out.println("获取map键值对:" + m + "-" + n));
        hashOps.delete( skuId );
    }
}

package com.janice.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import gmall.bean.UserAddress;
import gmall.service.OrderService;
import gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Reference
    UserService userService;

    public List<UserAddress> initOrder(String userId) {
        System.out.println("用户id:"+userId);
        List<UserAddress> addressList = userService.getUserAddressList(userId);
        return addressList;
    }
}

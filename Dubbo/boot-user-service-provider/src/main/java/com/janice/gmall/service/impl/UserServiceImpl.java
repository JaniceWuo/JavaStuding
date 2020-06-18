package com.janice.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import gmall.bean.UserAddress;
import gmall.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Service  //暴露服务
@Component
public class UserServiceImpl implements UserService {
    public List<UserAddress> getUserAddressList(String userId) {
        UserAddress address1 = new UserAddress(1, "北京市", "1", "李老师", "010-56253825", "Y");
        UserAddress address2 = new UserAddress(2, "深圳市", "1", "王老师", "010-56253825", "N");
        return Arrays.asList(address1,address2);
    }
}

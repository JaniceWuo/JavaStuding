package com.janice.sportmanage.controller;

import com.alibaba.fastjson.JSON;
import com.janice.sportmanage.bean.User;
import com.janice.sportmanage.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    UserDao userDao;
    @RequestMapping("/login")
    public String login(@RequestBody User user){
        String flag = "error";
        User loginUser = userDao.getUserByMessage(user.getUsername(), user.getPassword());
        Map<String,Object> map = new HashMap<>();
        if(loginUser!=null){
            flag =  "ok";
        }
        map.put("flag",flag);
        map.put("user",user);
        map.put("token","token"+user.getUsername()+user.getPassword());
        return JSON.toJSONString(map);
//        System.out.println(user);
//        return "ok";  //因为前端设置了要判断是ok才通过
    }
}

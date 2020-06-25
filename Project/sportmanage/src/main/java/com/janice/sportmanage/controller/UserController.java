package com.janice.sportmanage.controller;


import com.alibaba.fastjson.JSON;
import com.janice.sportmanage.bean.QueryInfo;
import com.janice.sportmanage.bean.User;
import com.janice.sportmanage.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserDao userDao;

    @RequestMapping("/allUser")
    public String getUserList(QueryInfo queryInfo){
        System.out.println(queryInfo);
        int numbers = userDao.getUserCounts("%"+queryInfo.getQuery()+"%");// 获取数据总数？ 在没有输入用户名字时 查询的数据库全部数据
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        System.out.println("当前起始为第"+pageStart+"条");
        List<User> users = userDao.getAllUser("%"+queryInfo.getQuery()+"%",pageStart,queryInfo.getPageSize());
        Map<String, Object> res = new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",users);
        System.out.println(res);

        return JSON.toJSONString(res);
    }

    @RequestMapping("/addUser")
    public String addUser(@RequestBody User user){
        System.out.println(user);
        user.setRole("普通用户");
        user.setState(false);
        int i = userDao.addUser(user);  //插入操作如果成功的话会返回1
//        System.out.println(i);
        String str = i >0?"success":"error";
        return str;
    }

    @RequestMapping("/getUpdate")
    public String getUpdateUser(@RequestParam("id") int id){
        System.out.println("编号"+id);
        User updateUser = userDao.getUpdateUser(id);
        return JSON.toJSONString(updateUser);
    }

    @RequestMapping("/editUser")
    public String editUser(@RequestBody User user){
        System.out.println(user);
        int i = userDao.editUser(user);
        String str = i >0?"success":"error";
        return str;
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(int id){
        System.out.println(id);
        int i = userDao.deleteUser(id);
        String str = i >0?"success":"error";
        return str;
    }

    //修改用户的状态  是否是超级管理员
    @RequestMapping("/userState")
    public String updateUserState(@RequestParam("id") Integer  id,
                                  @RequestParam("state") Boolean state){
        int i = userDao.updateState(id, state);
        System.out.println("用户编号:"+id);
        System.out.println("用户状态:"+state);
        String str = i >0?"success":"error";
        return str;
    }
}

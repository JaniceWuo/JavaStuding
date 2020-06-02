package cn.controller;

import cn.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/testException")
    public String testException() throws SysException {
        System.out.println("testException运行了...");
        //模拟异常
        try {
            int a = 10/0;
        } catch (Exception e) {
            e.printStackTrace();
            //抛出自定义异常信息
            throw new SysException("出错了...");
        }
        return "success";
    }
}

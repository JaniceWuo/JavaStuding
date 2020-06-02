package cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制器
 */
@Controller
@RequestMapping(path = "/user")
public class HelloController {
    @RequestMapping("/hello")
    public String SayHello(){
        System.out.println("hello mvc");
        return "success";
    }

    @RequestMapping(path = "/testRequestMapping")
    public String testRequestMapping(){
        System.out.println("测试RequestMapping");
        return "success";
    }
}

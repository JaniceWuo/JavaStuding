package cn.controller;

import cn.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;

/**
 * 常用注解
 */
@Controller
@RequestMapping("/anno")
@SessionAttributes(value = {"msg"})
public class AnnoController {
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(name = "name") String username){
        System.out.println("执行了");
        System.out.println(username);
        return "success";
    }

    /**
     * pathVariable注解
     * @param id
     * @return
     */
    @RequestMapping("/testPathVariable/{sid}")
    public String testPathVariable(@PathVariable(name = "sid") String id){
        System.out.println("执行了");
        System.out.println(id);
        return "success";
    }

    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookieValue){
        System.out.println("执行了");
        System.out.println(cookieValue);
        return "success";
    }

    @RequestMapping(value = "/testModelAttribute")
    public String testModelAttribute(User user){
        System.out.println("testModelAttribute执行了");
        System.out.println(user);
        return "success";
    }

//    @ModelAttribute
//    public User showUser(String uname){
//        System.out.println("showUser执行了");
//        //模拟查询数据库
//        User user = new User();
//        user.setUname(uname);
//        user.setAge(20);
//        user.setDate(new Date());
//        return user;
//    }

    @RequestMapping(value = "/testSessionAttributes")
    public String testSessionAttributes(Model model)
    {
        System.out.println("testSessionAttributes");
        //存储到request域中
        model.addAttribute("msg","hello");
        return "success";
    }

    /**
     * 从session域中取值（要先存才有值）
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/getSessionAttributes")
    public String getSessionAttributes(ModelMap modelMap)
    {
        System.out.println("getSessionAttributes");
        //存储到request域中
        String msg = (String) modelMap.get("msg");
        System.out.println(msg);
        return "success";
    }

    /**
     * 删除清除
     * @param status
     * @return
     */
    @RequestMapping(value = "/delSessionAttributes")
    public String delSessionAttributes(SessionStatus status)
    {
        System.out.println("delSessionAttributes");
        status.setComplete();
        return "success";
    }

}

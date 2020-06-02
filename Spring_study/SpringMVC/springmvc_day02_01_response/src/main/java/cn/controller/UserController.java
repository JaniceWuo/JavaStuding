package cn.controller;

import cn.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/testString")
    public String testString(Model model){
        System.out.println("testString执行了");
        User user = new User();
        user.setUsername("xiaohong");
        user.setAge(20);
        user.setPassword("123");
        model.addAttribute("user",user);
        return "success";
    }

    /**
     * void
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("testVoid执行了");
        //请求转发
//        request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);
        //重定向
//        response.sendRedirect(request.getContextPath() + "/index.jsp");

        //直接响应
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("你好");
        return;
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView mv = new ModelAndView();
        System.out.println("testModelAndView执行了");
        User user = new User();
        user.setUsername("小凤");
        user.setAge(20);
        user.setPassword("123");

        //把user对象存储到mv对象中，也会把user对象存入到request对象
        mv.addObject("user",user);
        mv.setViewName("success");  //会用视图解析器
        return mv;
    }

    /**
     * 使用关键字方式进行转发或重定向
     * @return
     */
    @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect(){
        System.out.println("testForwardOrRedirect执行了");

//        return "forward:/WEB-INF/pages/success.jsp";
        return "redirect:/index.jsp";
    }

    /**
     * 模拟异步请求和响应
     */
//    @RequestMapping("/testAjax")
//    public void testAjax(@RequestBody String body){
//        System.out.println("testAjax方法执行了");
//        System.out.println(body);
//    }

    /**
     * Ajax 自动封装
     * @param user
     */
    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user){
        System.out.println("testAjax方法执行了");
        System.out.println(user);
        //模拟查询数据库
        user.setUsername("haha");
        user.setPassword("346");
        return user;
    }
}

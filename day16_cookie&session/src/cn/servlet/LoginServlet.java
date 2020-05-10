package cn.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置request编码
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkcode = request.getParameter("checkcode");
        //先获取生成的验证码
        HttpSession session = request.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        //完成上述操作后验证码并不是一次性的  要删除session中存储的验证码
        session.removeAttribute("checkCode_session");

        if(checkCode_session!=null && checkCode_session.equalsIgnoreCase((checkcode))){
            //忽略大小写
            //验证码正确
            if("zhangsan".equals(username) && "123".equals(password)){
                //重定向
                session.setAttribute("user",username);
                response.sendRedirect(request.getContextPath()+"/success.jsp");//重定向是两次请求
            }else {
                //用户名密码错误
                request.setAttribute("login_error","用户名或密码错误");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }

        }else {
            //验证码不正确
            request.setAttribute("cc_error","验证码错误");
            //转发到登录页面
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

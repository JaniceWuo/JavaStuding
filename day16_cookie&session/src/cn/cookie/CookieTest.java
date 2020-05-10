package cn.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        if(cookies!=null && cookies.length>0){
            for(Cookie cookie:cookies){
                String name = cookie.getName();
                if("lastTime".equals(name)){
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日 HH:mm:ss");
                    String newDate = sdf.format(date);
                    //url编码 解决中文和空格等特殊字符
                    newDate = URLEncoder.encode(newDate,"utf-8");

                    cookie.setValue(newDate);
                    //设置cookie的存活时间
                    cookie.setMaxAge(60*60*24*30);
                    response.addCookie(cookie);
                    String value = cookie.getValue();
                    //url解码
                    value = URLDecoder.decode(value,"utf-8");
                    response.getWriter().write("<h1>欢迎回来，您上次的访问时间为:"+value+"</h1>");
                    break;
                }
            }
        }

        if(cookies==null || cookies.length==0 || flag==false){
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日 HH:mm:ss");
            String newDate = sdf.format(date);
            newDate = URLEncoder.encode(newDate,"utf-8");
            Cookie cookie = new Cookie("lastTime",newDate);
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);
            response.getWriter().write("<h1>您好，欢迎您首次访问</h1>");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

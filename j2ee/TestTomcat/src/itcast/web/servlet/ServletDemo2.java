package itcast.web.servlet;


import javax.servlet.*;
import java.io.IOException;

/*
servlet方法
 */
public class ServletDemo2 implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init....");
    }


    /*
    获取ServletConfig对象
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service....");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    /*
    销毁方法 在服务器关闭时执行 执行一次
     */
    @Override
    public void destroy() {
        System.out.println("destroy...");
    }
}

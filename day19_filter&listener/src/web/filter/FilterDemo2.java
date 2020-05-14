package web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/*")
public class FilterDemo2 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("filterDemo2执行了...");
        chain.doFilter(req, resp);
        System.out.println("filterDemo2回来了...");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

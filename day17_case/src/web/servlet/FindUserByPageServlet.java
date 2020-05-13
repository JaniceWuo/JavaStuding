package web.servlet;

import domain.PageBean;
import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //要获取当前页数以及一页展示多少条
        String currentPage = request.getParameter("currentPage");  //就是会有这样一个参数的  如果前端页面还没有写好，可以直接在地址后面加上?currentPage=1
        String rows = request.getParameter("rows");
//        System.out.println(currentPage);
        if(currentPage==null || "".equals(currentPage)){
            currentPage = "1";
        }
        if(rows==null || "".equals(rows)){
            rows = "2";
        }

        //获取条件查询参数
        Map<String, String[]> condition = request.getParameterMap();
//        System.out.println("condition:"+condition);

        request.setAttribute("condition",condition);

        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage,rows,condition);
//        System.out.println(pb);
        request.setAttribute("pb",pb);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

package web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 40;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //创建画笔对象
        Graphics g = image.getGraphics();
        g.setColor(Color.PINK);
        g.fillRect(0,0,width,height);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("黑体",Font.BOLD,28));
//        g.drawRect(0,0,width-1,height-1);

        //写验证码
        String str = "ABCDEFGabcdefg0123456789";
        Random r = new Random();
        StringBuilder sb = new StringBuilder();


        for(int i = 1;i<=4;i++){
            char ct = str.charAt(r.nextInt(str.length()));
            sb.append(ct);
            g.drawString(ct + "",width/5*i-5,height/2+10);
        }
        String checkCode_session = sb.toString();
        //将验证码存入session
        request.getSession().setAttribute("checkCode_session",checkCode_session);

        //随机生成点 画干扰线
//        g.setColor(Color.GREEN);
//        for(int i = 0; i < 5;i++){
//            int x1 = r.nextInt(width);
//            int x2 = r.nextInt(width);
//            int y1 = r.nextInt(height);
//            int y2 = r.nextInt(height);
//            g.drawLine(x1,x2,y1,y2);
//        }

        ImageIO.write(image,"jpg",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}

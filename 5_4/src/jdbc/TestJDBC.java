package jdbc;

import java.sql.*;

public class TestJDBC {
    public static void execute(String sql){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try(  Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/how2java?useSSL=false","root","aptx4869.");
              Statement s = c.createStatement();
              )
        {
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/how2java?useSSL=false",
                "root", "aptx4869.");
             Statement s = c.createStatement();)
        {
            String sql = "select count(*) from hero";
//            String name = "xiaohong";
//            String password = "ppapa";
//            String sql = String.format("select * from user where name = '%s' and password = '%s'",name,password);
//            System.out.println(sql);
            ResultSet rs = s.executeQuery(sql);
            int total = 0;
            while(rs.next()){
                total = rs.getInt(1);
//                System.out.println("密码正确");
            }
            System.out.println(total);
            // 不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
            // rs.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

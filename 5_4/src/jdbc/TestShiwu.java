package jdbc;

import java.sql.*;
import java.util.Scanner;

public class TestShiwu {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }


        try(Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/how2java?useSSL=false","root","aptx4869.");
            Statement s1 = c.createStatement(); Statement s2 = c.createStatement();
        )
        {
            c.setAutoCommit(false);
            ResultSet rs = s1.executeQuery("select * from hero limit 0,10");
            int id = 0;
            int count = 0;
            while(rs.next()){
                id = rs.getInt(1);
                System.out.println("试图删除id为"+id+"的数据");
                String delete = "delete from hero where id = " + id;
                s2.execute(delete);
                count++;
            }
            Scanner in = new Scanner(System.in);
            while(count==10){
                System.out.println("是否要删除数据(Y/N)");
                String ans = in.next();
                if(ans.equals("Y")){
                    System.out.println("提交删除");
                    c.commit();
                    break;
                }else{
                    if(ans.equals("N")){
                        System.out.println("删除失败");
                        break;
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

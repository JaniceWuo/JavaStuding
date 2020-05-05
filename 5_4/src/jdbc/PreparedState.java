package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedState {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String sql = "insert into hero values(null,?,?,?)";
        try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/how2java?useSSL=false",
                "root","aptx4869.");
            PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1,"提莫");
            ps.setFloat(2,313.0f);
            ps.setInt(3,50);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

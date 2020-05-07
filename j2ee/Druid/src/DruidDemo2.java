import utils.JDBCutils;

import java.sql.Connection;
import java.sql.SQLException;

public class DruidDemo2 {
    public static void main(String[] args) {
        /*
        完成添加的操作
         */
        //1.获取连接
        try {
            Connection connection = JDBCutils.getConnection();
            String sql = "insert into dic values(null,?,?)";
            /*
            往数据集里插入数据   我没写
             */
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

package utils;

//import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
druid连接池的工具类
 */
public class JDBCutils {
    private static DataSource ds;
    static {
        try {
            Properties pro = new Properties();
            pro.load(JDBCutils.class.getClassLoader().getResourceAsStream("druid.properties"));
//            ds = DruidDataSourceFactory.createDataSource(pro);
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }

    public static void close(Statement statement, Connection connection){
//        if(statement!=null){
//            try {
//                statement.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//
//        if(connection!=null){
//            try {
//                connection.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
        close(null,statement,connection);
    }


    public static void close(ResultSet rs,Statement statement, Connection connection){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static DataSource getDataSource(){
        return ds;
    }
}

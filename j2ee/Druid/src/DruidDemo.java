import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo {
    public static void main(String[] args) throws Exception {
        Properties pro = new Properties();
        InputStream is = DruidDemo.class.getResourceAsStream("druid.properties");
        pro.load(is);
        //获取连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);

        //获取连接
        Connection connection = ds.getConnection();
        System.out.println(connection);
    }
}

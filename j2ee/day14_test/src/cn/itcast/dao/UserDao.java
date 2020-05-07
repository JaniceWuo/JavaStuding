package cn.itcast.dao;

import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/*
操作数据库中user表的类
 */
public class UserDao {
    //声明JDBCTemplate对象共用
//    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    //登录方法
    public ResultSet login(User loginUser){
        String sql = "select * from user where username = ? and password = ?";
        ResultSet rs = null;
        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,"superbaby");
            pst.setString(2,"123");
            rs = pst.executeQuery();
//            User user = rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        User user = template.queryForObject(sql,
//                new BeanPropertyRowMapper<User>(User.class),
//                loginUser.getUsername(),loginUser.getPassword());
        return rs;
    }
}

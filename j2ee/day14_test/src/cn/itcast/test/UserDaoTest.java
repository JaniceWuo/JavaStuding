package cn.itcast.test;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoTest {
    public static void testLogin() throws SQLException {
        User loginUser = new User();
        loginUser.setUsername("superbaby");
        loginUser.setPassword("123");
        UserDao dao = new UserDao();
        System.out.println(loginUser);
        User user = new User();
//        User user = dao.login(loginUser);
//        System.out.println(dao.login(loginUser));
        ResultSet rs = dao.login(loginUser);
        if(rs.next()){
            try {
                String name = rs.getString("username");
                String pass = rs.getString("password");
                int id = rs.getInt(1);
                user.setUsername(name);
                user.setPassword(pass);
                user.setId(id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        System.out.println(user);

    }

    public static void main(String[] args) throws SQLException {
        UserDaoTest.testLogin();
    }


}

package com.janice.sportmanage.dao;

import com.janice.sportmanage.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    public User getUserByMessage(@Param("username") String username, @Param("password") String password);
    public List<User> getAllUser(@Param("username") String username, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);
    public int getUserCounts(@Param("username") String username);
    public int addUser(User user);
    public int editUser(User user);
    public User getUpdateUser(int id);
    public int deleteUser(int id);
    public int updateState(Integer id, Boolean state);
}

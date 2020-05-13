package service;

import domain.PageBean;
import domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {
    public List<User> findAll();
    public User login(User user);

    /**
     * 保存user
     * @param user
     */
    void addUser(User user);

    void deleteUser(String id);

    User findUserBtId(String id);  //根据id查询

    void updateUser(User user);

    void delSelectedUser(String[] uids);

    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);  //分页条件查询

}

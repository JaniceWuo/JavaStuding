package dao;

import domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的Dao
 */
public interface UserDao {
    public List<User> finall();
    public User findUserByNamePass(String username,String password);

    void add(User user);

    void delete(int parseInt);  //删除id号的用户数据

    User findById(int parseInt);

    void update(User user);


    /**
     * 分页查询
     * @return
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);

    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}

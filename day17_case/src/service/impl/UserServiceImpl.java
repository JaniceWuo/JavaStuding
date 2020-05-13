package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.PageBean;
import domain.User;
import service.UserService;

import java.util.List;
import java.util.Map;

/**
 * 完成全部的功能
 */
public class UserServiceImpl implements UserService{
    private UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        //调用Dao完成查询
        return dao.finall();
    }

    @Override
    public User login(User user) {
        return dao.findUserByNamePass(user.getUsername(),user.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findUserBtId(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] uids) {
        //遍历数组
        if(uids!=null && uids.length > 0){
            for(String id:uids){
                dao.delete(Integer.parseInt(id));
            }
        }

    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);

        if(currentPage<=0){
            currentPage = 1;
        }

        int rows = Integer.parseInt(_rows);
        PageBean<User> pb = new PageBean<User>();

        pb.setRows(rows);
        int totalCount = dao.findTotalCount(condition);  //总记录数
        pb.setTotalCount(totalCount);


        //计算总页码数
        int totalPage = totalCount % rows == 0 ? totalCount/rows:(totalCount/rows)+1;
        pb.setTotalPage(totalPage);

        if(currentPage >= totalPage+1){
            currentPage = totalPage;
        }

        pb.setCurrentPage(currentPage);

        //计算开始的索引
        int start = (currentPage-1)*rows;
        List<User> list = dao.findByPage(start,rows,condition);
        pb.setList(list);

        return pb;
    }
}

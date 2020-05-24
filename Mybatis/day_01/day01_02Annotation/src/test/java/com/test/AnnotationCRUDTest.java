package com.test;

import com.dao.IUserDao;
import com.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class AnnotationCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

//    @Test
//    public void testSave(){
//        User user = new User();
//        user.setUsername("annotation");
//        user.setAddress("北京昌平");
//        userDao.saveUser(user);
//    }

//    @Test
//    public void testUpdate(){
//        User user = new User();
//        user.setId(41);
//        user.setUsername("annotation");
//        user.setAddress("北京");
//        user.setSex("男");
//        user.setBirthday(new Date());
//        userDao.updateUser(user);
//    }

//    @Test
//    public void testFindById(){
//        User user = new User();
//        System.out.println(userDao.findById(34));
//    }

    @Test
    public void testFindByName(){
//        User user = new User();
        System.out.println(userDao.findUserByName("%annotation%"));
    }

}

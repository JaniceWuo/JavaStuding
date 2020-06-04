package com.test;

import com.dao.IAccountDao;
import com.domain.Account;
import com.service.IAccountService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class testMyBatis {
    @Test
    public void run1() throws Exception {
        //加载mybatis配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession对象
        SqlSession session = factory.openSession();
        //获取代理对象
        IAccountDao dao = session.getMapper(IAccountDao.class);
//        //查询所有数据
//        List<Account> list = dao.findAll();
//        for(Account account:list){
//            System.out.println(account);
//        }
        Account account = new Account();
        account.setName("熊大");
        account.setMoney(200d);
        dao.saveAccount(account);
        //提交事物
        session.commit();

        session.close();
        in.close();
    }
}

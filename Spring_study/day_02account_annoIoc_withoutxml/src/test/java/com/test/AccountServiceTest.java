package com.test;

import com.domain.Account;
import com.service.IAccountService;
import config.JdbcConfig;
import config.SpringConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * ContextConfiguration:
 *         location:xml文件位置
 *         classes:注解类位置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {
    @Autowired
    private IAccountService as = null;

//    @Before
//    public void init(){
//        ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        as = ac.getBean("accountService", IAccountService.class);
//    }

    @Test
    public void testFindAll() {
        List<Account> accounts = as.findAllAccount();
        for(Account account:accounts){
            System.out.println(account);
        }
    }
    @Test
    public void testFindOne() {
        Account account = as.findAccountById(1);
        System.out.println(account);
    }
    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("test Anno");
        account.setMoney(10f);

        as.saveAccount(account);
    }
    @Test
    public void testUpdate() {
        Account account = as.findAccountById(1);
        account.setName("小绿");
        as.updateAccount(account);
    }
    @Test
    public void testDelete() {
        as.deleteAccount(2);
    }
}

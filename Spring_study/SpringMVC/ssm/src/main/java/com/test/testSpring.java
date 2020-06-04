package com.test;

import com.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testSpring {
    @Test
    public void run1(){
        //加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");

        //获取对象
        IAccountService as = (IAccountService) ac.getBean("accountService");
        //调用方法
        as.findAll();
    }
}

package com.jdbctemplate;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 最基本用法
 */
public class JdbcTemplateDemo2 {
    public static void main(String[] args) {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate",JdbcTemplate.class);
        //执行操作
        jt.execute("insert into account(name,money) values ('ddd',1000)");


        //spring的内置数据源
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://127.0.0.1:3307/eesy");
//        ds.setUsername("root");
//        ds.setPassword("admin");
//        //创建对象
//        JdbcTemplate jt = new JdbcTemplate();
//        jt.setDataSource(ds);
//        jt.execute("insert into account(name,money) values('ccc',1000)");
    }
}

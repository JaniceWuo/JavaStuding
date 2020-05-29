package com.jdbctemplate;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 最基本用法
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        //spring的内置数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://127.0.0.1:3307/eesy");
        ds.setUsername("root");
        ds.setPassword("admin");
        //创建对象
        JdbcTemplate jt = new JdbcTemplate();
        jt.setDataSource(ds);
        jt.execute("insert into account(name,money) values('ccc',1000)");
    }
}

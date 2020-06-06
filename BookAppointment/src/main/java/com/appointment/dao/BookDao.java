package com.appointment.dao;

import com.appointment.domain.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BookDao {
    @Select("select * from book where bookId = #{bookIdd}")
    Book queryById(String bookIdd);   //根据id查询书籍

    @Select("select * from book where name like '%' #{name} '%'")
    List<Book> querySome(String name);  //根据名字进行模糊查询？？？

    @Update("update book set number = number-1 where bookId = #{bookId} and number > 0")
    public int updateNumber(Integer bookId);
}

package com.appointment.dao;

import com.appointment.domain.Student;
import org.apache.ibatis.annotations.Select;

public interface StudentDao {
    //验证登录的用户名和密码
    @Select("select * from student where studentId = #{studentId} and password = #{password}")
    Student queryStudent(Student student);   //如果有该用户 就返回  没有就是null
}

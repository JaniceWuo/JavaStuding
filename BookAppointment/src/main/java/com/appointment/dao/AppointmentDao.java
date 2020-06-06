package com.appointment.dao;

import com.appointment.domain.Appointment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AppointmentDao {

    @Insert("insert ignore into appointment (bookId, studentId) VALUES (#{bookId},#{studentId})")
    void insertAppointment(@Param("bookId") Integer bookId, @Param("studentId") String studentId);

//    @Select("select a.bookId,a.studentId,a.appoint_time,b.name,b.introd,b.number from appointment a inner join book b on a.bookId = b.bookId where a.studentId = #{studentId}")
    @Select("select * from appointment where studentId = #{studentId}")
    @Results(id = "appointMap",value = {
            @Result(id = true,column = "bookId",property = "bookId"),
            @Result(id = true,column = "studentId",property = "studentId"),
            @Result(column = "appoint_time",property = "appoint_time"),
            @Result(property = "book",column = "bookId",one = @One(select = "com.appointment.dao.BookDao.queryById",fetchType = FetchType.EAGER))
//意思是后面还要把数据封装到book属性中  使用appointment中的bookId去查
    })
    List<Appointment> queryAndReturn(@Param("studentId") String studentId);  //通过学生id查看预约了哪些书
}

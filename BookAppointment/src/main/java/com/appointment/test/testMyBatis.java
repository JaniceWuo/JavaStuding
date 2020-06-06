package com.appointment.test;

import com.appointment.dao.AppointmentDao;
import com.appointment.dao.BookDao;
import com.appointment.dao.StudentDao;
import com.appointment.domain.Appointment;
import com.appointment.domain.Book;
import com.appointment.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class testMyBatis {
    @Test
    public void run() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession session = factory.openSession();
//        StudentDao dao = session.getMapper(StudentDao.class);
//        Student student = new Student();
//        student.setStudentId("617");
//        student.setPassword("123");
//        Student student1 = dao.queryStudent(student);
//        System.out.println(student1);

        BookDao dao = session.getMapper(BookDao.class);
//        String bookName = "设计";
//        List<Book> books = dao.querySome(bookName);
//        for(Book book:books){
//            System.out.println(book);
//        }

        dao.updateNumber(1000);
        session.commit();
        session.close();
//        System.out.println(res);
    }

    @Test
    public void testAppoint() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession session = factory.openSession();
        AppointmentDao dao = session.getMapper(AppointmentDao.class);
        List<Appointment> appointments = dao.queryAndReturn("618");
        for (Appointment appointment:appointments){
            System.out.println(appointment);
            System.out.println(appointment.getBook());
        }
//        System.out.println(appointments);
//        System.out.println(appointments);
//        dao.insertAppointment(1000,"618");
//        session.commit();
//        session.close();
    }
}

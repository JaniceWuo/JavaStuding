package com.appointment.domain;

import java.util.Date;

public class Appointment {
    private Integer bookId;
    private String studentId;
    private Date appoint_time;
    private Book book;

    public Appointment() {
    }

    public Appointment(Integer bookId, String studentId, Date appoint_time, Book book) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.appoint_time = appoint_time;
        this.book = book;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getAppoint_time() {
        return appoint_time;
    }

    public void setAppoint_time(Date appoint_time) {
        this.appoint_time = appoint_time;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "bookId=" + bookId +
                ", studentId=" + studentId +
                ", appoint_time=" + appoint_time +
//                ", book=" + book +
                '}';
    }
}

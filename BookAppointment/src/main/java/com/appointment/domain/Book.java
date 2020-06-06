package com.appointment.domain;

import java.io.Serializable;

public class Book implements Serializable {
    private Integer bookId;
    private String name;
    private int number;
    private String introd;

    public Book() {
    }

    public Book(Integer bookId, String name, int number, String introd) {
        this.bookId = bookId;
        this.name = name;
        this.number = number;
        this.introd = introd;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getIntrod() {
        return introd;
    }

    public void setIntrod(String introd) {
        this.introd = introd;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", introd='" + introd + '\'' +
                '}';
    }
}

package ru.aston.hw2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {
    private String name;
    private List<Book> bookList;

    public Student(String name) {
        this.name = Objects.requireNonNull(name, "Должно быть имя");
        this.bookList = new ArrayList<Book>();
    }

    public Student(String name, List<Book> bookList) {
        this.name = Objects.requireNonNull(name, "Должно быть имя");
        this.bookList = new ArrayList<Book>(bookList);
    }

    @Override
    public String toString() {
        return "Student { name=" + name + ", bookList: count=" + bookList.size() + " {" + bookList + "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBookList() {
        return new ArrayList<Book>(bookList);
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = new ArrayList<Book>(bookList);
    }
}
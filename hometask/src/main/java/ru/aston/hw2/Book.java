package ru.aston.hw2;

import java.util.Objects;

public class Book {
    private String name;
    private String author;
    private int pages;
    private int year;

    public Book(String name, String author, int pages, int year) {
        this.name = Objects.requireNonNull(name, "Название книги не должно быть пустым");
        this.author = author;
        this.pages = pages;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Book other = (Book)obj;
        return Objects.equals(this.name, other.name) && Objects.equals(this.author, other.author) && Objects.equals(this.pages, other.pages) && Objects.equals(this.year, other.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, pages, year);
    }

    @Override
    public String toString() {
        return "Book{ name=" + name + ", author=" + author + ", pages=" + pages + ", year=" + year + "}";
    }
}
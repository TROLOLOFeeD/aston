package ru.aston.hw2;

import java.util.ArrayList;
import java.util.List;

public class StreamParser {
    // Парсер полученной строки стрима на экземпляры Student

    public static Student parse(String line) {
        String[] parts = line.split(": ", 2);
        String name = parts[0];
        String books = parts[1];

        String[] booksStrings = books.split("; ");

        List<Book> listBooks = new ArrayList<>();
        for (String bookStr : booksStrings) {
            String[] book = bookStr.split(", ");

            String title = book[0];
            title = title.replace("\"", "");
            String author = book[1];
            int pages = Integer.parseInt(book[2]);
            int year = Integer.parseInt(book[3].replace(".", ""));

            listBooks.add(new Book(title, author, pages, year));
        }

        return new Student(name, listBooks);
    }
}

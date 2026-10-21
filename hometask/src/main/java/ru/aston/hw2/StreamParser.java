package ru.aston.hw2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StreamParser {
    // Парсер полученной строки стрима на экземпляры Student

    private static final Logger LOGGER = Logger.getLogger(StreamParser.class.getName());

    public static Student parse(String line, int lineNum) {
        if (line == null || line.trim().isEmpty()) {
            String msg = "Ошибка в строке " + lineNum + ": пустая или null строка";
            LOGGER.log(Level.SEVERE, msg);
            throw new RuntimeException(msg);
        }

        String[] parts = line.split(": ", 2);
        if (parts.length < 2) {
            String msg = String.format("Ошибка в строке %d: отсутствует разделитель ': '. Данные: '%s'", lineNum, line);
            LOGGER.log(Level.SEVERE, msg);
            throw new RuntimeException(msg);
        }

        String name = parts[0].trim();
        String booksStr = parts[1].trim();
        List<Book> listBooks = new ArrayList<>();

        if (!booksStr.isEmpty()) {
            String[] booksStrings = booksStr.split("; ");

            for (int i = 0; i < booksStrings.length; i++) {
                String bookStr = booksStrings[i].trim();
                if (bookStr.isEmpty()) continue;

                String[] bookParts = bookStr.split(", ");

                if (bookParts.length < 4) {
                    String msg = String.format("Ошибка в строке %d, книга %d: ожидается как минимум 4 поля (Название, Автор, Страницы, Год), найдено %d. Данные: '%s'", 
                            lineNum, i + 1, bookParts.length, bookStr);
                    LOGGER.log(Level.SEVERE, msg);
                    throw new RuntimeException(msg);
                }

                try {
                    String yearStr = bookParts[bookParts.length - 1].replace(".", "").trim();
                    String pagesStr = bookParts[bookParts.length - 2].trim();
                    String author = bookParts[bookParts.length - 3].trim();
                    
                    String[] titleParts = Arrays.copyOfRange(bookParts, 0, bookParts.length - 3);
                    String title = String.join(", ", titleParts).replace("\"", "").trim();

                    int pages = Integer.parseInt(pagesStr);
                    int year = Integer.parseInt(yearStr);

                    listBooks.add(new Book(title, author, pages, year));
                } catch (NumberFormatException e) {
                    String msg = String.format("Ошибка в строке %d, книга %d: некорректное числовое значение (страницы или год). Данные: '%s'. Причина: %s", 
                            lineNum, i + 1, bookStr, e.getMessage());
                    LOGGER.log(Level.SEVERE, msg, e);
                    throw new RuntimeException(msg, e);
                }
            }
        }
        lineNum++;

        return new Student(name, listBooks);
    }
}

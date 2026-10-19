package ru.aston.hw2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student {
    private String name;
    private List<Book> bookList;

    public Student(String name) {
        this.name = Objects.requireNonNull(name, "Должно быть имя");
        this.bookList = new ArrayList<Book>(); // пустая инициализация
    }

    public Student(String name, List<Book> bookList) {
        this.name = Objects.requireNonNull(name, "Должно быть имя");
        this.bookList = new ArrayList<Book>(bookList); // защита от случайного переписывания
    }

    @Override
    public boolean equals(Object obj) {
        Student other = (Student) obj;
        return Objects.equals(this.name, other.name) && Objects.equals(this.bookList, other.bookList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bookList);
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
        return new ArrayList<Book>(bookList); // защита от случайного переписывания
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = new ArrayList<Book>(bookList); // защита от случайного переписывания
    }

    public static void main(String[] args) {
        Path path = Path.of("hw2.txt");

        try (Stream<String> input = Files.lines(path)) {
            List<Student> students = input.map(line -> {
                String[] parts = line.split(": ", 2); //student: bookList
                String name = parts[0];
                String books = parts[1];

                String[] booksStrings = books.split("; "); //book1; book2

                List<Book> listBooks = new ArrayList<>();
                for (String bookStr : booksStrings) {
                    String[] book = bookStr.split(", "); //title, author, pages, year

                    String title = book[0];
                    title = title.replace("\"", "");
                    String author = book[1];
                    int pages = Integer.parseInt(book[2]);
                    int year = Integer.parseInt(book[3].replace(".", "")); // year.

                    listBooks.add(new Book(title, author, pages, year));
                }

                return new Student(name, listBooks);
            }).collect(Collectors.toList());

            students.stream()
                    .peek(System.out::println)
                    .map(Student::getBookList)
                    .flatMap(List::stream)
                    .sorted(Comparator.comparingInt(Book::getPages))
                    .distinct()
                    .filter(book -> book.getYear() > 2000)
                    .limit(3)
                    .map(Book::getYear)
                    .findFirst()
                    .ifPresentOrElse( //if (optional.isPresent)
                            year -> System.out.println("Найден год выпуска: " + year),
                            () -> System.out.println("Книга, выпущенная после 2000 года, отсутствует в выборке"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
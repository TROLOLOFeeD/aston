package ru.aston.hw2;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static final String rscName = "hw2.txt";
    static final int filterYear = 2000;
    static final int filterLimit = 3;

    public static void main(String[] args) throws IOException {
        /**
        * Получение строк в FileReader
        * Парсинг в экземпляры Student в StreamParser
        * Main - вывод
        */ 

        try (Stream<String> input = FileReader.readFile(rscName)) {
            List<Student> students = input.map(line -> StreamParser.parse(line)).collect(Collectors.toList());

            students.stream()
                    .peek(System.out::println)
                    .map(Student::getBookList)
                    .flatMap(List::stream)
                    .sorted(Comparator.comparingInt(Book::getPages))
                    .distinct()
                    .filter(book -> book.getYear() > filterYear)
                    .limit(filterLimit)
                    .map(Book::getYear)
                    .findFirst()
                    .ifPresentOrElse(
                            year -> System.out.println("Найден год выпуска: " + year),
                            () -> System.out.println("Книга, выпущенная после 2000 года, отсутствует в выборке"));
        }
    }
}

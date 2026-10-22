package ru.aston.hw2;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final String RESOURCE_NAME = "hw2.txt";
    private static final int PUBLISHED_AFTER_YEAR = 2000;
    private static final int BOOKS_LIMIT = 3;

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        /**
         * Получение строк в FileReader
         * Парсинг в экземпляры Student в StreamParser
         * Main - вывод
         */

        try (Stream<String> input = FileReader.readFile(RESOURCE_NAME)) {

            AtomicInteger lineNum = new AtomicInteger(1);

            List<Student> students = input.map(line -> StreamParser.parse(line, lineNum.getAndIncrement()))
                    .collect(Collectors.toList());

            students.stream()
                    .peek(System.out::println)
                    .map(Student::getBookList)
                    .flatMap(List::stream)
                    .sorted(Comparator.comparingInt(Book::getPages))
                    .distinct()
                    .filter(book -> book.getYear() > PUBLISHED_AFTER_YEAR)
                    .limit(BOOKS_LIMIT)
                    .map(Book::getYear)
                    .findFirst()
                    .ifPresentOrElse(
                            year -> System.out.println("Найден год выпуска: " + year),
                            () -> System.out.println(String
                                    .format("Книга, выпущенная после %d года, отсутствует в выборке", PUBLISHED_AFTER_YEAR)));

        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, "Критическая ошибка при обработке данных: " + e.getMessage(), e);
            System.err.println("Программа завершена. Проверьте формат данных: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Ошибка ввода-вывода: " + e.getMessage(), e);
            System.err.println("Программа завершена из-за ошибки системы.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Неожиданная ошибка: " + e.getMessage(), e);
            System.err.println("Программа завершена из-за непредвиденной ошибки.");
        }
    }
}

package ru.aston.hw2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class FileReader {
    // Получение стрим-строк из файла

    @SuppressWarnings("resource")
    public static Stream<String> readFile(String resourceName) throws IOException {
        InputStream in = FileReader.class.getClassLoader().getResourceAsStream(resourceName);
        if (in == null) {
            throw new FileNotFoundException("Не найдено" + resourceName);
        }
        return new BufferedReader(new InputStreamReader(in)).lines();
    }
}

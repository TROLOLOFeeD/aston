package ru.aston.hw2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FileReader {
    // Получение стрим-строк из файла
    
    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());
    
    public static Stream<String> readFile(String resourceName) throws FileNotFoundException {
        InputStream in = FileReader.class.getClassLoader().getResourceAsStream(resourceName);
        if (in == null) {
            LOGGER.log(Level.SEVERE, "Файл " + resourceName + "не найден в ресурсах");
            throw new FileNotFoundException("Файл " + resourceName + "не найден в ресурсах");
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            return reader.lines().onClose(() -> {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, "Ошибка при закрытии потока чтения", e);
                }
            });
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ошибка инициализации чтения " + resourceName, e);
            throw new RuntimeException("Ошибка инициализации чтения " + resourceName, e);
        }
    }
}

package ru.aston.hw3.decorator;

import ru.aston.hw3.builder.Pizza;
import java.util.Map;
import java.util.logging.Logger;

public class LoggerPizzaBuilder implements PizzaBuilderInterface {
    private static final Logger logger = Logger.getLogger(LoggerPizzaBuilder.class.getName());
    private final PizzaBuilderInterface wrappedBuilder;

    public LoggerPizzaBuilder(PizzaBuilderInterface builder) {
        this.wrappedBuilder = builder;
    }

    @Override
    public PizzaBuilderInterface addTopping(String product, int quantity) {
        logger.info("Добавляем топпинг: " + product + " в количестве " + quantity);
        try {
            wrappedBuilder.addTopping(product, quantity);
            return this;
        } catch (Exception e) {
            logger.severe("Ошибка при добавлении топпинга '" + product + "': " + e.getMessage());
            throw e;
        }
    }

    @Override
    public PizzaBuilderInterface addToppings(Map<String, Integer> toppings) {
        logger.info("Добавляем набор топпингов: " + toppings);
        try {
            wrappedBuilder.addToppings(toppings);
            return this;
        } catch (Exception e) {
            logger.severe("Ошибка при добавлении списка топпингов '" + toppings + "': " + e.getMessage());
            throw e;
        }
    }

    @Override
    public PizzaBuilderInterface addCheese() {
        logger.info("Добавляем сыр");
        wrappedBuilder.addCheese();
        return this;
    }

    @Override
    public PizzaBuilderInterface addTomato() {
        logger.info("Добавляем помидор");
        wrappedBuilder.addTomato();
        return this;
    }

    @Override
    public PizzaBuilderInterface withSauce(String sauceType) {
        logger.info("Добавляем соус: " + sauceType);
        try {
            wrappedBuilder.withSauce(sauceType);
            return this;
        } catch (Exception e) {
            logger.severe("Ошибка при добавлении соуса '" + sauceType + "': " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Pizza build() {
        logger.info("Собираем пиццу");
        return wrappedBuilder.build();
    }
}
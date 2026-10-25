package ru.aston.hw3.decorator;

import ru.aston.hw3.adapter.Burger;
import ru.aston.hw3.adapter.BurgerBuilder;
import java.util.logging.Logger;

public class LoggerBurgerBuilder implements BurgerBuilder {
    private static final Logger logger = Logger.getLogger(LoggerBurgerBuilder.class.getName());
    private final BurgerBuilder wrappedBuilder;

    public LoggerBurgerBuilder(BurgerBuilder builder) {
        this.wrappedBuilder = builder;
    }

    @Override
    public BurgerBuilder addIngredient(String ingredient, int quantity) {
        logger.info("Добавляем ингредиент: " + ingredient + " (" + quantity + ")");
        try {
            wrappedBuilder.addIngredient(ingredient, quantity);
            return this;
        } catch (Exception e) {
            logger.severe("Ошибка при добавлении ингридиента '" + ingredient + "': " + e.getMessage());
            throw e;
        }
    }

    @Override
    public BurgerBuilder addFloors(int floors) {
        logger.info("Добавляем этажей: " + floors);
        try {
            wrappedBuilder.addFloors(floors);
            return this;
        } catch (Exception e) {
            logger.severe("Ошибка при изменении этажности : " + e.getMessage());
            throw e;
        }
    }

    @Override
    public BurgerBuilder addFloors() {
        logger.info("Добавляем один этаж");
        wrappedBuilder.addFloors();
        return this;
    }

    @Override
    public BurgerBuilder addMeat() {
        logger.info(" Добавляем котлету");
        wrappedBuilder.addMeat();
        return this;
    }

    @Override
    public BurgerBuilder addCheese() {
        logger.info("Добавляем сыр");
        wrappedBuilder.addCheese();
        return this;
    }

    @Override
    public BurgerBuilder addTomato() {
        logger.info("Добавляем помидор");
        wrappedBuilder.addTomato();
        return this;
    }

    @Override
    public BurgerBuilder withSauce(String sauceType) {
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
    public Burger build() {
        logger.info("Собираем бургер");
        return wrappedBuilder.build();
    }
}
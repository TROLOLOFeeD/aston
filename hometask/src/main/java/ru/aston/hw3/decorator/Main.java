package ru.aston.hw3.decorator;

import ru.aston.hw3.adapter.Burger;
import ru.aston.hw3.adapter.BurgerBuilder;
import ru.aston.hw3.adapter.Pizza2BurgerAdapter;
import ru.aston.hw3.builder.Pizza;
import ru.aston.hw3.builder.PizzaBuilder;

public class Main {

    public static void main(String[] args) {
        PizzaBuilderInterface pizzaBuilder = new PizzaBuilder();
        PizzaBuilderInterface loggerBuilder = new LoggerPizzaBuilder(pizzaBuilder);

        Pizza pizza = loggerBuilder.addCheese().addTomato().withSauce("Барби'кю").addTopping("Томату", 1).build();

        System.out.println(pizza);

        BurgerBuilder burgerBuilder = new Pizza2BurgerAdapter();
        BurgerBuilder loggingBurgerBuilder = new LoggerBurgerBuilder(burgerBuilder);

        Burger burger = loggingBurgerBuilder.addMeat().addCheese().addTomato().withSauce("Гаукакуле").addFloors(-1)
                .build();

        System.out.println(burger);
    }
}

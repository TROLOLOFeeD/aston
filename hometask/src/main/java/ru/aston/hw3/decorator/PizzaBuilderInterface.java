package ru.aston.hw3.decorator;

import java.util.Map;

import ru.aston.hw3.builder.Pizza;

public interface PizzaBuilderInterface {
    PizzaBuilderInterface addTopping(String product, int quantity);

    PizzaBuilderInterface addToppings(Map<String, Integer> toppings);

    PizzaBuilderInterface addCheese();

    PizzaBuilderInterface addTomato();

    PizzaBuilderInterface withSauce(String sauceType);

    Pizza build();
}
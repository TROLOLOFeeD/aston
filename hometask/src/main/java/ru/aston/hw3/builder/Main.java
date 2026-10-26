package ru.aston.hw3.builder;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Билдер тест
        // вопрос: нужно ли для подобных тестов (которые по идее должны быть в test)
        //      сохранять значения как private и называть UPPER_SNAKE_CASE?

        Pizza pizza1 = new PizzaBuilder().build();

        Pizza pizza2 = new PizzaBuilder().addCheese().addTomato().addTopping("колбасы", 2).addTopping("сыр", 5)
                .withSauce("1000 чертей").build();

        Map<String, Integer> TopList = new LinkedHashMap<>(Map.of("Ананасы", 5, "Рыбов", 1));

        Pizza pizza3 = new PizzaBuilder().addCheese().addTomato().addToppings(TopList).addTopping("рыбов", 4).build();

        System.out.println(pizza1);
        System.out.println(pizza2);
        System.out.println(pizza3);

    }
}
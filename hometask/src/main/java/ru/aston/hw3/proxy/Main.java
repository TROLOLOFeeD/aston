package ru.aston.hw3.proxy;

import ru.aston.hw3.adapter.Burger;
import ru.aston.hw3.adapter.BurgerBuilder;
import ru.aston.hw3.adapter.Pizza2BurgerAdapter;
import ru.aston.hw3.builder.Pizza;
import ru.aston.hw3.builder.PizzaBuilder;
import ru.aston.hw3.decorator.LoggerBurgerBuilder;
import ru.aston.hw3.decorator.LoggerPizzaBuilder;
import ru.aston.hw3.decorator.PizzaBuilderInterface;

public class Main {

    public static void main(String[] args) {
        PizzaBuilderInterface pizzaBuilder = new PizzaBuilder();
        LoggerPizzaBuilder pizzaDecorator = new LoggerPizzaBuilder(pizzaBuilder);
        PizzaBuilderProxy pizzaProxy = new PizzaBuilderProxy(pizzaDecorator);

        pizzaProxy.addCheese();
        pizzaProxy.addTomato();
        pizzaProxy.addTopping("пепперони", 3);

        try {
            Pizza pizza = pizzaProxy.build();
            System.out.println(pizza);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        pizzaProxy.pay();
        Pizza pizza = pizzaProxy.build();
        System.out.println(pizza);

        BurgerBuilder burgerBuilder = new Pizza2BurgerAdapter();
        LoggerBurgerBuilder burgerDecorator = new LoggerBurgerBuilder(burgerBuilder);
        BurgerBuilderProxy burgerProxy = new BurgerBuilderProxy(burgerDecorator);

        burgerProxy.addMeat();
        burgerProxy.addCheese();
        burgerProxy.addFloors(2);

        burgerProxy.pay();

        Burger burger = burgerProxy.build();
        System.out.println(burger);
    }
}

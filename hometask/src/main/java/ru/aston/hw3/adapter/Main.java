package ru.aston.hw3.adapter;

public class Main {
    public static void main(String[] args) {
        BurgerBuilder burgerBuilder = new Pizza2BurgerAdapter();

        Burger cheeseBurger = burgerBuilder
                .addMeat()
                .addCheese().addCheese()
                .addTomato()
                .addIngredient("Лук", 1)
                .addFloors()
                .withSauce("Таптап")
                .build();

        System.out.println(cheeseBurger);
    }
}

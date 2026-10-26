package ru.aston.hw3.adapter;

public interface BurgerBuilder {
    BurgerBuilder addIngredient(String ingredient, int quantity);

    BurgerBuilder addFloors(int floors);

    BurgerBuilder addFloors();

    BurgerBuilder addMeat();

    BurgerBuilder addCheese();

    BurgerBuilder addTomato();

    BurgerBuilder withSauce(String sauceType);

    Burger build();
}
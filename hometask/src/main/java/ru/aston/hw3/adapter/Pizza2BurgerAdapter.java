package ru.aston.hw3.adapter;

import ru.aston.hw3.builder.Pizza;
import ru.aston.hw3.builder.PizzaBuilder;

public class Pizza2BurgerAdapter implements BurgerBuilder {

    private final PizzaBuilder pizzaBuilder;
    private int floors = 1;

    public Pizza2BurgerAdapter() {
        this.pizzaBuilder = new PizzaBuilder();
    }

    @Override
    public BurgerBuilder addIngredient(String ingredient, int quantity) {
        if (ingredient == null || ingredient.isBlank()) {
            throw new IllegalArgumentException("Название ингредиента не может быть пустым");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество ингредиента должно быть больше 0");
        }
        pizzaBuilder.addTopping(ingredient, quantity);
        return this;
    }

    @Override
    public BurgerBuilder addFloors(int floors) {
        if (floors < 0) {
            throw new IllegalArgumentException("Количество этажей не может быть отрицательным");
        }
        this.floors += floors;
        return this;
    }

    @Override
    public BurgerBuilder addFloors() {
        return addFloors(1);
    }

    @Override
    public BurgerBuilder addMeat() {
        pizzaBuilder.addTopping("Котлета", 1);
        return this;
    }

    @Override
    public BurgerBuilder addCheese() {
        pizzaBuilder.addCheese();
        return this;
    }

    @Override
    public BurgerBuilder addTomato() {
        pizzaBuilder.addTomato();
        return this;
    }

    @Override
    public BurgerBuilder withSauce(String sauceType) {
        if (sauceType == null || sauceType.isBlank()) {
            throw new IllegalArgumentException("Название соуса не может быть пустым");
        }
        pizzaBuilder.withSauce(sauceType);
        return this;
    }

    @Override
    public Burger build() {
        Pizza pizza = pizzaBuilder.addTopping("Булка", 1 + floors).build();

        return new Burger(
                pizza.getToppings(),
                this.floors,
                pizza.hasCheese(),
                pizza.hasTomato(),
                pizza.getSauce());
    }
}
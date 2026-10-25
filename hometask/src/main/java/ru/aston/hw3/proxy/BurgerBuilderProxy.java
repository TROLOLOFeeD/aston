package ru.aston.hw3.proxy;

import ru.aston.hw3.adapter.Burger;
import ru.aston.hw3.adapter.BurgerBuilder;

public class BurgerBuilderProxy implements BurgerBuilder {
    private final BurgerBuilder builder;
    private boolean isPaid = false;
    private double totalCost = 0.0;
    private static final double PRICE_PER_INGREDIENT = 39.99;
    private static final double PRICE_PER_FLOOR = 29.99;

    public BurgerBuilderProxy(BurgerBuilder builder) {
        this.builder = builder;
    }

    public void pay() {
        this.isPaid = true;
        System.out.println("Оплата произведена. Чек на сумму: " + totalCost + " руб.");
    }

    public boolean isPaid() {
        return isPaid;
    }

    private void checkPayment() throws IllegalStateException {
        if (!isPaid) {
            throw new IllegalStateException(
                    "Нельзя собрать бургер без оплаты. Текущая стоимость: " + totalCost + " руб.");
        }
    }

    @Override
    public BurgerBuilder addIngredient(String ingredient, int quantity) {
        totalCost += quantity * PRICE_PER_INGREDIENT;
        System.out.println("Добавлен ингредиент: " + ingredient + " (" + quantity + "). Стоимость: +"
                + (quantity * PRICE_PER_INGREDIENT) + " руб.");
        return builder.addIngredient(ingredient, quantity);
    }

    @Override
    public BurgerBuilder addFloors(int floors) {
        totalCost += floors * PRICE_PER_FLOOR;
        System.out.println("Добавлено этажей: " + floors + ". Стоимость: +" + (floors * PRICE_PER_FLOOR) + " руб.");
        return builder.addFloors(floors);
    }

    @Override
    public BurgerBuilder addFloors() {
        return addFloors(1);
    }

    @Override
    public BurgerBuilder addMeat() {
        totalCost += PRICE_PER_INGREDIENT;
        System.out.println("Добавлена котлета. Стоимость: +" + PRICE_PER_INGREDIENT + " руб.");
        return builder.addMeat();
    }

    @Override
    public BurgerBuilder addCheese() {
        totalCost += PRICE_PER_INGREDIENT;
        System.out.println("Добавлен сыр. Стоимость: +" + PRICE_PER_INGREDIENT + " руб.");
        return builder.addCheese();
    }

    @Override
    public BurgerBuilder addTomato() {
        totalCost += PRICE_PER_INGREDIENT;
        System.out.println("Добавлен помидор. Стоимость: +" + PRICE_PER_INGREDIENT + " руб.");
        return builder.addTomato();
    }

    @Override
    public BurgerBuilder withSauce(String sauceType) {
        return builder.withSauce(sauceType);
    }

    @Override
    public Burger build() {
        checkPayment();
        System.out.println("Собираем бургер...");
        return builder.build();
    }
}
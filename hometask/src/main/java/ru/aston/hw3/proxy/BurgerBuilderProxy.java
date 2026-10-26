package ru.aston.hw3.proxy;

import ru.aston.hw3.adapter.Burger;
import ru.aston.hw3.adapter.BurgerBuilder;
import ru.aston.hw3.chain.Validation;
import ru.aston.hw3.chain.WorkingHours;
import ru.aston.hw3.common.OrderType;
import ru.aston.hw3.strategy.Pricer;
import ru.aston.hw3.strategy.StandardPricer;

import java.time.LocalTime;

public class BurgerBuilderProxy implements BurgerBuilder {
    private final BurgerBuilder builder;
    private boolean isPaid = false;
    private double totalCost = 0.0;

    private static final double PRICE_PER_INGREDIENT = 39.99;
    private static final double PRICE_PER_FLOOR = 29.99;

    private final Validation validation;
    private final Pricer pricer;
    private final OrderType orderType = OrderType.BURGER;

    private final LocalTime orderTime;

    public BurgerBuilderProxy(BurgerBuilder builder, Validation validationChain, Pricer pricingStrategy) {
        this(builder, validationChain, pricingStrategy, LocalTime.now());
    }

    public BurgerBuilderProxy(BurgerBuilder builder, Validation validation, Pricer pricer, LocalTime orderTime) {
        this.builder = builder;
        this.validation = validation;
        this.pricer = pricer;
        this.orderTime = orderTime;
    }

    public BurgerBuilderProxy(BurgerBuilder builder) {
        this(builder, new WorkingHours(LocalTime.of(9, 0), LocalTime.of(21, 0)), new StandardPricer());
    }

    public void pay() {
        double finalPrice = pricer.calculatePrice(totalCost, orderTime, orderType);
        this.isPaid = true;

        if (finalPrice < totalCost) {
            System.out.println("Оплата произведена. Чек на сумму: " + finalPrice
                    + " руб. (Применена скидка! Базовая цена: " + totalCost + " руб.)");
        } else {
            System.out.println("Оплата произведена. Чек на сумму: " + finalPrice + " руб.");
        }
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
        if (validation != null) {
            validation.validate(orderTime, orderType);
        }

        checkPayment();

        System.out.println("Собираем бургер...");
        return builder.build();
    }
}
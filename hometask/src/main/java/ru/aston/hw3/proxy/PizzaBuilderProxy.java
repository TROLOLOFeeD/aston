package ru.aston.hw3.proxy;

import ru.aston.hw3.builder.Pizza;
import ru.aston.hw3.decorator.PizzaBuilderInterface;
import java.util.Map;

public class PizzaBuilderProxy implements PizzaBuilderInterface {
    private final PizzaBuilderInterface builder;
    private boolean isPaid = false;
    private final double PRICE_PER_TOPPING = 49.99;
    private double totalCost = 0.0;

    public PizzaBuilderProxy(PizzaBuilderInterface builder) {
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
                    "Нельзя собрать пиццу без оплаты. Текущая стоимость: " + totalCost + " руб.");
        }
    }

    @Override
    public PizzaBuilderInterface addTopping(String product, int quantity) {
        this.totalCost += quantity * PRICE_PER_TOPPING;
        System.out.println("Добавлен топпинг: " + product + " (" + quantity + " шт.). Стоимость: +"
                + (quantity * PRICE_PER_TOPPING) + " руб.");
        return builder.addTopping(product, quantity);
    }

    @Override
    public PizzaBuilderInterface addToppings(Map<String, Integer> toppings) {
        for (Map.Entry<String, Integer> entry : toppings.entrySet()) {
            this.totalCost += (entry.getValue() * PRICE_PER_TOPPING);
        }
        return builder.addToppings(toppings);
    }

    @Override
    public PizzaBuilderInterface addCheese() {
        this.totalCost += PRICE_PER_TOPPING;
        System.out.println("Добавлен сыр. Стоимость: +" + PRICE_PER_TOPPING + " руб.");
        return builder.addCheese();
    }

    @Override
    public PizzaBuilderInterface addTomato() {
        this.totalCost += PRICE_PER_TOPPING;
        System.out.println("Добавлен помидор. Стоимость: +" + PRICE_PER_TOPPING + " руб.");
        return builder.addTomato();
    }

    @Override
    public PizzaBuilderInterface withSauce(String sauceType) {
        return builder.withSauce(sauceType);
    }

    @Override
    public Pizza build() {
        checkPayment();
        System.out.println("Собираем пиццу...");
        return builder.build();
    }
}
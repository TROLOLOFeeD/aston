package ru.aston.hw3.builder;

import java.util.LinkedHashMap;
import java.util.Map;

import ru.aston.hw3.decorator.PizzaBuilderInterface;

public class PizzaBuilder implements PizzaBuilderInterface {
    protected final Map<String, Integer> toppings = new LinkedHashMap<>();
    protected boolean hasCheese = false;
    protected boolean hasTomato = false;
    protected String sauce = "без соуса";

    public PizzaBuilderInterface addTopping(String product, int quantity) {
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("Название топпинга не может быть пустым");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Количество должно быть больше нуля");
        }
        this.toppings.merge(product.toLowerCase(), quantity, Integer::sum);
        return this;
    }

    public PizzaBuilderInterface addToppings(Map<String, Integer> toppings) {
        if (toppings == null) {
            throw new IllegalArgumentException("Список начинок не должен быть null");
        }
        for (Map.Entry<String, Integer> entry : toppings.entrySet()) {
            addTopping(entry.getKey().toLowerCase(), entry.getValue());
        }
        return this;
    }

    public PizzaBuilderInterface addCheese() {
        this.hasCheese = true;
        addTopping("Сыр", 1);
        return this;
    }

    public PizzaBuilderInterface addTomato() {
        this.hasTomato = true;
        addTopping("Помидор", 1);
        return this;
    }

    public PizzaBuilderInterface withSauce(String sauceType) {
        if (sauceType == null || sauceType.isBlank()) {
            throw new IllegalArgumentException("Название соуса не может быть пустым");
        }
        this.sauce = sauceType;
        return this;
    }

    public Pizza build() {
        return new Pizza(this);
    }
}
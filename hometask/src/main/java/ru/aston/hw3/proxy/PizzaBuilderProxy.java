package ru.aston.hw3.proxy;

import ru.aston.hw3.builder.Pizza;
import ru.aston.hw3.common.OrderType;
import ru.aston.hw3.chain.Validation;
import ru.aston.hw3.chain.WorkingHours;
import ru.aston.hw3.decorator.PizzaBuilderInterface;
import ru.aston.hw3.strategy.Pricer;
import ru.aston.hw3.strategy.StandardPricer;

import java.time.LocalTime;
import java.util.Map;

public class PizzaBuilderProxy implements PizzaBuilderInterface {

    private final PizzaBuilderInterface builder;
    private boolean isPaid = false;
    private final double PRICE_PER_TOPPING = 49.99;
    private double totalCost = 0.0;

    private final Validation validation;
    private final Pricer pricer;
    private final OrderType orderType = OrderType.PIZZA;

    private final LocalTime orderTime;

    public PizzaBuilderProxy(PizzaBuilderInterface builder, Validation validation, Pricer pricer, LocalTime orderTime) {
        this.builder = builder;
        this.validation = validation;
        this.pricer = pricer;
        this.orderTime = orderTime;
    }

    public PizzaBuilderProxy(PizzaBuilderInterface builder,
            Validation validation,
            Pricer pricer) {
        this(builder, validation, pricer, LocalTime.now());
    }

    public PizzaBuilderProxy(PizzaBuilderInterface builder) {
        this(builder, new WorkingHours(LocalTime.of(9, 0), LocalTime.of(21, 0)), new StandardPricer());
    }

    @Override
    public PizzaBuilderInterface addTopping(String product, int quantity) {
        this.totalCost += quantity * PRICE_PER_TOPPING;
        System.out.println("Добавлен топпинг: " + product + " (" + quantity
                + " шт.). Стоимость: +" + (quantity * PRICE_PER_TOPPING) + " руб.");
        builder.addTopping(product, quantity);
        return this;
    }

    @Override
    public PizzaBuilderInterface addToppings(Map<String, Integer> toppings) {
        if (toppings == null) {
            throw new IllegalArgumentException("Список топпингов не может быть null");
        }
        for (Map.Entry<String, Integer> entry : toppings.entrySet()) {
            this.totalCost += entry.getValue() * PRICE_PER_TOPPING;
        }
        builder.addToppings(toppings);
        return this;
    }

    @Override
    public PizzaBuilderInterface addCheese() {
        this.totalCost += PRICE_PER_TOPPING;
        System.out.println("Добавлен сыр. Стоимость: +" + PRICE_PER_TOPPING + " руб.");
        builder.addCheese();
        return this;
    }

    @Override
    public PizzaBuilderInterface addTomato() {
        this.totalCost += PRICE_PER_TOPPING;
        System.out.println("Добавлен помидор. Стоимость: +" + PRICE_PER_TOPPING + " руб.");
        builder.addTomato();
        return this;
    }

    @Override
    public PizzaBuilderInterface withSauce(String sauceType) {
        builder.withSauce(sauceType);
        return this;
    }

    private void validateOrder() {
        if (validation != null) {
            validation.validate(orderTime, orderType);
        }
    }

    public void pay() {
        validateOrder();

        if (isPaid) {
            System.out.println("Заказ уже оплачен.");
            return;
        }

        double finalPrice = pricer.calculatePrice(totalCost, orderTime, orderType);
        this.isPaid = true;

        System.out.println("Оплата произведена. Чек на сумму: " + finalPrice
                + " руб. (базовая цена: " + totalCost + " руб.)");
    }

    public boolean isPaid() {
        return isPaid;
    }

    private void checkPayment() {
        if (!isPaid) {
            throw new IllegalStateException(
                    "Нельзя собрать пиццу без оплаты. Текущая стоимость: "
                            + totalCost + " руб.");
        }
    }

    @Override
    public Pizza build() {
        validateOrder();

        checkPayment();

        System.out.println("Собираем пиццу...");

        return builder.build();
    }
}
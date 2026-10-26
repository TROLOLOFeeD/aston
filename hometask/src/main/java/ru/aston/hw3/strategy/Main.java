package ru.aston.hw3.strategy;

import ru.aston.hw3.builder.PizzaBuilder;
import ru.aston.hw3.chain.Validation;
import ru.aston.hw3.chain.WorkingHours;
import ru.aston.hw3.proxy.PizzaBuilderProxy;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Validation validation = new WorkingHours(LocalTime.of(9, 0), LocalTime.of(23, 0));
        PizzaBuilder baseBuilder = new PizzaBuilder();

        System.out.println("1. Заказ в 15:00 (Стандартная стратегия):");
        Pricer standardPricer = new StandardPricer();
        PizzaBuilderProxy proxy1 = new PizzaBuilderProxy(baseBuilder, validation, standardPricer, LocalTime.of(15, 0));

        proxy1.addCheese();
        proxy1.addTopping("Пепперони", 2);
        proxy1.pay();
        proxy1.build();
        System.out.println(proxy1);

        System.out.println("\n2. Заказ в 22:30 (Стратегия со скидкой 20%):");
        Pricer discountPricer = new LastHourDiscount(LocalTime.of(22, 0), 20.0);
        PizzaBuilderProxy proxy2 = new PizzaBuilderProxy(baseBuilder, validation, discountPricer, LocalTime.of(22, 30));

        proxy2.addCheese();
        proxy2.addTopping("Пепперони", 2);
        proxy2.pay();
        proxy2.build();
        System.out.println(proxy2);

        System.out.println("\n3. Заказ в 18:00 со стратегией скидки (скидка НЕ должна примениться):");
        PizzaBuilderProxy proxy3 = new PizzaBuilderProxy(baseBuilder, validation, discountPricer, LocalTime.of(18, 0));

        proxy3.addTomato();
        proxy3.pay();
        proxy3.build();
        System.out.println(proxy3);
    }
}
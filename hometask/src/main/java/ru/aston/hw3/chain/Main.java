package ru.aston.hw3.chain;

import ru.aston.hw3.adapter.Burger;
import ru.aston.hw3.adapter.Pizza2BurgerAdapter;
import ru.aston.hw3.builder.Pizza;
import ru.aston.hw3.builder.PizzaBuilder;
import ru.aston.hw3.chain.Validation;
import ru.aston.hw3.chain.WorkingHours;
import ru.aston.hw3.common.OrderType;
import ru.aston.hw3.proxy.BurgerBuilderProxy;
import ru.aston.hw3.proxy.PizzaBuilderProxy;
import ru.aston.hw3.strategy.StandardPricer;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        Validation workingHours = new WorkingHours(LocalTime.of(9, 0), LocalTime.of(21, 0));
        Validation noLatePizza = new NoLateNightPizzaValidation(LocalTime.of(20, 0));

        workingHours.setNext(noLatePizza);

        StandardPricer standardPricer = new StandardPricer();

        System.out.println("1. Заказ ПИЦЦЫ в 10:00 (Должен успешно пройти всю цепочку):");
        try {
            PizzaBuilderProxy proxy1 = new PizzaBuilderProxy(new PizzaBuilder(), workingHours, standardPricer,
                    LocalTime.of(10, 0));
            proxy1.addCheese();
            proxy1.pay();
            proxy1.build();
            System.out.println("Заказ успешно собран!\n");
        } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage() + "\n");
        }

        System.out.println("2. Заказ БУРГЕРА в 20:30 (Второе звено пропустит, т.к. это не пицца):");
        try {
            BurgerBuilderProxy proxy2 = new BurgerBuilderProxy(new Pizza2BurgerAdapter(), workingHours, standardPricer,
                    LocalTime.of(20, 30));
            proxy2.addMeat();
            proxy2.addCheese();
            proxy2.pay();
            proxy2.build();
            System.out.println("Заказ успешно собран!\n");
        } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage() + "\n");
        }

        System.out.println("3. Заказ ПИЦЦЫ в 20:30 (Второе звено цепи ДОЛЖНО отклонить заказ):");
        try {
            PizzaBuilderProxy proxy3 = new PizzaBuilderProxy(new PizzaBuilder(), workingHours, standardPricer,
                    LocalTime.of(20, 30));
            proxy3.addCheese();
            proxy3.pay();
            proxy3.build();
            System.out.println("Заказ успешно собран!\n");
        } catch (RuntimeException e) {
            System.out.println("Ошибка перехвачена: " + e.getMessage() + "\n");
        }

        System.out.println("4. Заказ БУРГЕРА в 22:00 (Первое звено цепи ДОЛЖНО отклонить заказ, второе не сработает):");
        try {
            BurgerBuilderProxy proxy4 = new BurgerBuilderProxy(new Pizza2BurgerAdapter(), workingHours, standardPricer,
                    LocalTime.of(22, 0));
            proxy4.addMeat();
            proxy4.pay();
            proxy4.build();
        } catch (RuntimeException e) {
            System.out.println("Ошибка перехвачена: " + e.getMessage() + "\n");
        }
    }
}
package ru.aston.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.aston.hw3.Adapter.Burger;
import ru.aston.hw3.Adapter.BurgerBuilder;
import ru.aston.hw3.Adapter.PizzaAdapter2Burger;
import ru.aston.hw3.Builder.Pizza;
import ru.aston.hw3.Builder.PizzaBuilder;
import ru.aston.hw3.Builder.Topping;

public class Main {

    public static void main(String[] args) {
        // Билдер

        Pizza pizza1 = new PizzaBuilder().build();

        Pizza pizza2 = new PizzaBuilder().addCheese().addTomato().addTopping("Колбаса", 2).addTopping("Каперсы", 5)
                .withSauce("1000 чертей").build();

        List<Topping> TopList = new ArrayList<Topping>(Arrays.asList(new Topping("Ананасы", 5), new Topping("Рыбов")));

        Pizza pizza3 = new PizzaBuilder().addCheese().addTopingList(TopList).build();

        System.out.println(pizza1);
        System.out.println(pizza2);
        System.out.println(pizza3);


        // Адаптер

        BurgerBuilder builder = new PizzaAdapter2Burger();
        
        Burger burger = builder
            .addToping("Котлетос говяжий", 1)
            .addCheese()
            .addTomato()
            .addSauce("ketchup")
            .build();
        
        System.out.println(burger);
    }
}

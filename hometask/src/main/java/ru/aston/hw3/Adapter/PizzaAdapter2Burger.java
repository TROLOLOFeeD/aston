package ru.aston.hw3.Adapter;

import java.util.ArrayList;
import java.util.List;

import ru.aston.hw3.Builder.Pizza;
import ru.aston.hw3.Builder.PizzaBuilder;
import ru.aston.hw3.Builder.Topping;

public class PizzaAdapter2Burger implements BurgerBuilder {
    private PizzaBuilder pizzaBuilder;
    private List<Topping> topList = new ArrayList<Topping>();
    
    public PizzaAdapter2Burger() {
        this.pizzaBuilder = new PizzaBuilder();
    }
    
    @Override
    public BurgerBuilder addTopingList(List<Topping> TopList) {
        this.pizzaBuilder.addTopingList(TopList);
        this.topList.addAll(TopList);
        return this;
    }

    @Override
    public BurgerBuilder addToping(Topping topping) {
        this.pizzaBuilder.addTopping(topping);
        this.topList.add(topping);
        return this;
    }

    @Override
    public BurgerBuilder addToping(String product, int quantity) {
        Topping newTop = new Topping(product, quantity);
        this.pizzaBuilder.addTopping(newTop);
        this.topList.add(newTop);
        return this;
    }


    @Override
    public BurgerBuilder addCheese() {
        this.pizzaBuilder.addCheese();
        return this;
    }

    @Override
    public BurgerBuilder addTomato() {
        this.pizzaBuilder.addTomato();
        return this;
    }

    @Override
    public BurgerBuilder addSauce(String sauce) {
        this.pizzaBuilder.withSauce(sauce);
        return this;
    }

    @Override
    public Burger build() {
        Pizza pizza = this.pizzaBuilder.build();

        return new Burger(
            pizza.isHasTesto(),
            this.topList,
            pizza.isHasCheese(),
            pizza.isHasTomato(),
            pizza.getSauce()
        );
    }   
}
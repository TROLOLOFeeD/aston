package ru.aston.hw3.Builder;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {
    protected boolean hasTesto = true;
    protected List<Topping> toppingList = new ArrayList<Topping>();
    protected boolean hasCheese = false;
    protected boolean hasTomato = false;
    protected String sauce = "No sauce";

    public PizzaBuilder addTopingList(List<Topping> TopList) {
        this.toppingList.addAll(TopList);
        return this;
    }

    public PizzaBuilder addTopping(Topping addTop) {
        this.toppingList.add(addTop);
        return this;
    }

    public PizzaBuilder addTopping(String product, int quantity) {
        this.toppingList.add(new Topping(product, quantity));
        return this;
    }

    public PizzaBuilder addCheese() {
        this.hasCheese = true;
        return this;
    }

    public PizzaBuilder addTomato() {
        this.hasTomato = true;
        return this;
    }

    public PizzaBuilder withSauce(String sauceType) {
        this.sauce = new String(sauceType);
        return this;
    }

    public Pizza build() {
        if (!hasTesto) {
            throw new IllegalStateException("Пицца без теста");
        }
        return new Pizza(this);
    }
}

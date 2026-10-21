package ru.aston.hw3.Builder;

import java.util.List;

public class Pizza {
    private boolean hasTesto;
    private List<Topping> toppingList;
    private boolean hasCheese;
    private boolean hasTomato;
    private String sauce;

    Pizza(PizzaBuilder builder) {
        this.hasTesto = builder.hasTesto;
        this.toppingList = builder.toppingList;
        this.hasCheese = builder.hasCheese;
        this.hasTomato = builder.hasTomato;
        this.sauce = builder.sauce;
    }

    public String getSauce() {
        return new String(sauce);
    }

    public boolean isHasTesto() {
        return hasTesto;
    }

    public List<Topping> getTopping() {
        return toppingList;
    }

    public boolean isHasCheese() {
        return hasCheese;
    }

    public boolean isHasTomato() {
        return hasTomato;
    }

    @Override
    public String toString() {
        return "Pizza [hasTesto=" + hasTesto + ", Topping=" + (toppingList.isEmpty() ? "без начинки" : toppingList)
                + ", hasCheese=" + hasCheese
                + ", hasTomato=" + hasTomato + ", sauce=" + sauce + "]";
    }
}

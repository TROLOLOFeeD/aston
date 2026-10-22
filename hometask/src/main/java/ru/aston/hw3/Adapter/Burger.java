package ru.aston.hw3.Adapter;

import java.util.List;

import ru.aston.hw3.Builder.Topping;


public class Burger {
    private boolean hasBulka;
    private List<Topping> toppingList;
    private boolean hasCheese;
    private boolean hasTomato;
    private String sauce;
    
    public Burger(boolean hasBulka, List<Topping> toppingList, boolean hasCheese, 
                  boolean hasTomato, String sauce) {
        this.hasBulka = hasBulka;
        this.toppingList = toppingList;
        this.hasCheese = hasCheese;
        this.hasTomato = hasTomato;
        this.sauce = sauce;
    }

    public boolean isHasBulka() {
        return hasBulka;
    }

    public List<Topping> getToppingList() {
        return toppingList;
    }

    public boolean isHasCheese() {
        return hasCheese;
    }

    public boolean isHasTomato() {
        return hasTomato;
    }

    public String getSauce() {
        return sauce;
    }

    @Override
    public String toString() {
        return "Burger [hasBulka=" + hasBulka + ", toppingList=" + toppingList + ", hasCheese=" + hasCheese
                + ", hasTomato=" + hasTomato + ", sauce=" + sauce + "]";
    }
}

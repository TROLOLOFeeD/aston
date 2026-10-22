package ru.aston.hw3.adapter;

import java.util.Collections;
import java.util.Map;

public class Burger {
    private final Map<String, Integer> ingredients;
    private final int floors;
    private final boolean hasCheese;
    private final boolean hasTomato;
    private final String sauce;

    public Burger(Map<String, Integer> ingredients, int floors, boolean hasCheese, boolean hasTomato, String sauce) {
        this.ingredients = Collections.unmodifiableMap(ingredients);
        this.floors = floors;
        this.hasCheese = hasCheese;
        this.hasTomato = hasTomato;
        this.sauce = sauce;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    public int getFloors() {
        return floors;
    }

    public boolean hasCheese() {
        return hasCheese;
    }

    public boolean hasTomato() {
        return hasTomato;
    }

    public String getSauce() {
        return sauce;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Burger{");
        sb.append("ingredients=").append(ingredients.isEmpty() ? "пустая булка" : ingredients);
        sb.append(", floors=").append(floors);
        if (!hasCheese)
            sb.append(", без сыра");
        if (!hasTomato)
            sb.append(", без помидора");
        sb.append(", sauce='").append(sauce).append('\'');
        sb.append('}');
        if (!hasTomato && !hasCheese && "без соусов".equals(sauce))
            sb.append(" ---Дайте этому парню булочку---");
        return sb.toString();
    }
}
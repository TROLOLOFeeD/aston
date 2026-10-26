package ru.aston.hw3.builder;

import java.util.Collections;
import java.util.Map;

public class Pizza {
    private final Map<String, Integer> toppings;
    private final boolean hasCheese;
    private final boolean hasTomato;
    private final String sauce;

    Pizza(PizzaBuilder builder) {
        this.toppings = Collections.unmodifiableMap(builder.toppings);
        this.hasCheese = builder.hasCheese;
        this.hasTomato = builder.hasTomato;
        this.sauce = builder.sauce;
    }

    public Map<String, Integer> getToppings() {
        return toppings;
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
        StringBuilder sb = new StringBuilder("Pizza{");
        sb.append("toppings=").append(toppings.isEmpty() ? "без начинки" : toppings);
        if (!hasCheese)
            sb.append(", без сыра");
        if (!hasTomato)
            sb.append(", без помидорок");
        sb.append(", sauce='").append(sauce).append('\'');
        sb.append('}');
        if (toppings.isEmpty() && !hasCheese && !hasTomato && "без соуса".equals(sauce))
            sb.append(" ---А зачем вообще такую пиццу брать???---");
        return sb.toString();
    }
}
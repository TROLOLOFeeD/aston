package ru.aston.hw3.Adapter;

import java.util.List;

import ru.aston.hw3.Builder.Topping;

public interface BurgerBuilder {
    BurgerBuilder addTopingList(List<Topping> topList);
    BurgerBuilder addToping(Topping topping);
    BurgerBuilder addToping(String product, int quantity);
    BurgerBuilder addCheese();
    BurgerBuilder addTomato();
    BurgerBuilder addSauce(String sauce);
    Burger build();
}

package ru.aston.hw3.Builder;

public class Topping {
    protected String product;
    protected int quantity = 1;

    public Topping(String product, int quantity) {
        this.product = new String(product);
        this.quantity = quantity;
    }

    public String getProduct() {
        return new String(product);
    }

    public int getQuantity() {
        return quantity;
    }

    public Topping(String product) {
        this.product = new String(product);
    }

    @Override
    public String toString() {
        return product + " " + quantity + "шт.";
    }
}

package ru.aston.hw3.strategy;

import ru.aston.hw3.common.OrderType;
import java.time.LocalTime;

public class StandardPricer implements Pricer {
    @Override
    public double calculatePrice(double basePrice, LocalTime orderTime, OrderType orderType) {
        return basePrice;
    }
}
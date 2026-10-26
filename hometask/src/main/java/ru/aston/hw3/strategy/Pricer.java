package ru.aston.hw3.strategy;

import ru.aston.hw3.common.OrderType;
import java.time.LocalTime;

public interface Pricer {
    double calculatePrice(double basePrice, LocalTime orderTime, OrderType orderType);
}
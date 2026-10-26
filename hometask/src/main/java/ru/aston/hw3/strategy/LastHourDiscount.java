package ru.aston.hw3.strategy;

import ru.aston.hw3.common.OrderType;
import java.time.LocalTime;

public class LastHourDiscount implements Pricer {
    private final LocalTime lastHourStart;
    private final double discountPercentage;

    public LastHourDiscount(LocalTime lastHourStart, double discountPercentage) {
        this.lastHourStart = lastHourStart;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double calculatePrice(double basePrice, LocalTime orderTime, OrderType orderType) {
        if (!orderTime.isBefore(lastHourStart)) {
            double discount = basePrice * (discountPercentage / 100.0);
            System.out.println("Применена скидка " + discountPercentage + "% (последний рабочий час)! Сэкономлено: "
                    + discount + " руб.");
            return basePrice - discount;
        }
        return basePrice;
    }
}
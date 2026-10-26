package ru.aston.hw3.chain;

import ru.aston.hw3.common.OrderType;
import java.time.LocalTime;

public class NoLateNightPizzaValidation extends AbstractValidation {
    private final LocalTime pizzaCutoffTime;

    public NoLateNightPizzaValidation(LocalTime pizzaCutoffTime) {
        this.pizzaCutoffTime = pizzaCutoffTime;
    }

    @Override
    public void validate(LocalTime orderTime, OrderType orderType) {
        if (orderType == OrderType.PIZZA && !orderTime.isBefore(pizzaCutoffTime)) {
            throw new RuntimeException(
                    "Заказ отклонен: Пицца не готовится после " + pizzaCutoffTime +
                            ". Попробуйте заказать бургер!");
        }
        checkNext(orderTime, orderType);
    }
}
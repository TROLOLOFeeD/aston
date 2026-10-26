package ru.aston.hw3.chain;

import ru.aston.hw3.common.OrderType;
import java.time.LocalTime;

public class WorkingHours extends AbstractValidation {
    private final LocalTime openTime;
    private final LocalTime closeTime;

    public WorkingHours(LocalTime openTime, LocalTime closeTime) {
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    @Override
    public void validate(LocalTime orderTime, OrderType orderType) {
        if (orderTime.isBefore(openTime) || orderTime.isAfter(closeTime)) {
            throw new RuntimeException(
                    "Заказ отклонен: мы не работаем в это время (" + orderTime + "). " +
                            "Рабочие часы: " + openTime + " - " + closeTime);
        }
        checkNext(orderTime, orderType);
    }
}
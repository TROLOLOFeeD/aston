package ru.aston.hw3.chain;

import ru.aston.hw3.common.OrderType;
import java.time.LocalTime;

public abstract class AbstractValidation implements Validation {
    protected Validation nextHandler;

    @Override
    public Validation setNext(Validation handler) {
        this.nextHandler = handler;
        return handler;
    }

    protected void checkNext(LocalTime orderTime, OrderType orderType) throws RuntimeException {
        if (nextHandler != null) {
            nextHandler.validate(orderTime, orderType);
        }
    }
}
package ru.aston.hw3.chain;

import ru.aston.hw3.common.OrderType;
import java.time.LocalTime;

public interface Validation {
    Validation setNext(Validation handler);

    void validate(LocalTime orderTime, OrderType orderType) throws RuntimeException;
}
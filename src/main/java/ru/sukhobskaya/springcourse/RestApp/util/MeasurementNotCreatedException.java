package ru.sukhobskaya.springcourse.RestApp.util;

public class MeasurementNotCreatedException extends RuntimeException {

    public MeasurementNotCreatedException (String msg) {
        super(msg);
    }
}

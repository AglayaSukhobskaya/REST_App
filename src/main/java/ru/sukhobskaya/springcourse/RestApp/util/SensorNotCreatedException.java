package ru.sukhobskaya.springcourse.RestApp.util;

public class SensorNotCreatedException extends RuntimeException {

    public SensorNotCreatedException(String msg) {
        super(msg);
    }
}

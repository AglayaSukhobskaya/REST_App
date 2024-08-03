package ru.sukhobskaya.sensor.util;

import org.springframework.stereotype.Component;
import ru.sukhobskaya.sensor.model.Sensor;

import java.util.Objects;

@Component
public class SensorValidator {

    public void validateSensorExist(String name, Sensor sensor) {
        if (Objects.isNull(sensor)) {
            var message = String.format("Sensor with the name <%s> does not exist", name);
            throw new SensorException(message);
        }
    }

    public void validateSensorDuplicate(String name, Sensor sensor) {
        if (Objects.nonNull(sensor)) {
            var message = String.format("Sensor with the name <%s> already exists", name);
            throw new SensorException(message);
        }
    }
}

package ru.sukhobskaya.springcourse.sensor.util;

import org.springframework.stereotype.Component;
import ru.sukhobskaya.springcourse.sensor.model.Sensor;

import java.util.Objects;

@Component
public class SensorValidator {

    public void validateSensorExist(String sensorName, Sensor sensor) {
        if (Objects.isNull(sensor)) {
            var message = String.format("Sensor with the name <%s> does not exist", sensorName);
            throw new SensorException(message);
        }
    }

    public void validateSensorDuplicate(String sensorName, Sensor sensor) {
        if (Objects.nonNull(sensor)) {
            var message = String.format("Sensor with the name <%s> already exists", sensorName);
            throw new SensorException(message);
        }
    }
}

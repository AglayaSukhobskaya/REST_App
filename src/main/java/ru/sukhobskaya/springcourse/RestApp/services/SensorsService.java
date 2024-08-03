package ru.sukhobskaya.springcourse.RestApp.services;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sukhobskaya.springcourse.RestApp.model.Sensor;
import ru.sukhobskaya.springcourse.RestApp.repositories.SensorsRepository;
import ru.sukhobskaya.springcourse.RestApp.util.SensorValidator;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SensorsService {
    SensorsRepository sensorsRepository;
    SensorValidator sensorValidator;

    public Sensor findByName(String name) {
        return sensorsRepository.findByName(name).orElse(null);
    }

    @Transactional
    public void create(String sensorName) {
        var sensor = findByName(sensorName);
        sensorValidator.validateSensorDuplicate(sensorName, sensor);
        var newSensor = new Sensor();
        newSensor.setName(sensorName);
        sensorsRepository.saveAndFlush(newSensor);
    }
}

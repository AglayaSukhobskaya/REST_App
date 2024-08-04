package ru.sukhobskaya.sensor.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.sukhobskaya.sensor.model.Sensor;
import ru.sukhobskaya.sensor.repository.SensorRepository;
import ru.sukhobskaya.sensor.util.SensorValidator;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SensorService {
    SensorRepository sensorRepository;
    SensorValidator sensorValidator;

    public List<String> findAll() {
        return sensorRepository.findAllSensorNames();
    }

    public Sensor findByName(String name) {
        return sensorRepository.findByName(name).orElse(null);
    }

    public void create(String name) {
        var sensor = findByName(name);
        sensorValidator.validateSensorDuplicate(name, sensor);
        var newSensor = Sensor.builder()
                .name(name)
                .build();
        sensorRepository.saveAndFlush(newSensor);
    }
}

package ru.sukhobskaya.springcourse.RestApp.services;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sukhobskaya.springcourse.RestApp.model.Sensor;
import ru.sukhobskaya.springcourse.RestApp.repositories.SensorRepository;
import ru.sukhobskaya.springcourse.RestApp.util.SensorValidator;

import java.util.List;

@Service
@Transactional(readOnly = true)
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

    @Transactional
    public void create(String sensorName) {
        var sensor = findByName(sensorName);
        sensorValidator.validateSensorDuplicate(sensorName, sensor);
        var newSensor = Sensor.builder()
                .name(sensorName)
                .build();
        sensorRepository.saveAndFlush(newSensor);
    }


}

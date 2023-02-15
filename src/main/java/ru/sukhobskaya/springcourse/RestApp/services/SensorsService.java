package ru.sukhobskaya.springcourse.RestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sukhobskaya.springcourse.RestApp.models.Sensor;
import ru.sukhobskaya.springcourse.RestApp.repositories.SensorsRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {
    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public Sensor findByName(String name) {
        Optional<Sensor> foundSensor = sensorsRepository.findByName(name);
        return foundSensor.orElse(null);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
    }
}

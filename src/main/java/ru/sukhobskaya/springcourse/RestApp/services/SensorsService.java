package ru.sukhobskaya.springcourse.RestApp.services;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sukhobskaya.springcourse.RestApp.model.Sensor;
import ru.sukhobskaya.springcourse.RestApp.repositories.SensorsRepository;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SensorsService {
    SensorsRepository sensorsRepository;

    public Sensor findByName(String name) {
        return sensorsRepository.findByName(name).orElse(null);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorsRepository.saveAndFlush(sensor);
    }
}

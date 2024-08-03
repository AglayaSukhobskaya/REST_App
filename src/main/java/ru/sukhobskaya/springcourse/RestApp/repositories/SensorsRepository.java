package ru.sukhobskaya.springcourse.RestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sukhobskaya.springcourse.RestApp.model.Sensor;

import java.util.Optional;

public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
}

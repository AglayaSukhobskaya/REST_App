package ru.sukhobskaya.sensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sukhobskaya.sensor.model.Sensor;

import java.util.List;
import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    @Query("select s.name from Sensor s")
    List<String> findAllSensorNames();
    Optional<Sensor> findByName(String name);
}

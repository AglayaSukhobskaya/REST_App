package ru.sukhobskaya.sensor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sukhobskaya.sensor.model.Measurement;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findByIsRainyTrue();
}

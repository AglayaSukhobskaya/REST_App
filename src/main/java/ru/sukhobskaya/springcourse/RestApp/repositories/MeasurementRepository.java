package ru.sukhobskaya.springcourse.RestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sukhobskaya.springcourse.RestApp.model.Measurement;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findByIsRainyTrue();
}

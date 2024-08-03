package ru.sukhobskaya.springcourse.RestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sukhobskaya.springcourse.RestApp.model.Measurement;

import java.util.List;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findByRainingTrue();
}

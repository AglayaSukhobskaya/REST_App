package ru.sukhobskaya.springcourse.RestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sukhobskaya.springcourse.RestApp.models.Measurement;

import java.util.List;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findByRainingTrue();
}

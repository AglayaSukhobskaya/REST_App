package ru.sukhobskaya.springcourse.RestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sukhobskaya.springcourse.RestApp.models.Measurement;
import ru.sukhobskaya.springcourse.RestApp.repositories.MeasurementsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementsRepository.save(measurement);
    }

    public int rainyDaysCount() {
        List<LocalDate> dates = measurementsRepository.findByRainingTrue().stream()
                .map(Measurement::getCreatedAt)
                .map(LocalDateTime::toLocalDate)
                .collect(Collectors.toList());

        return dates.size();
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
    }
}

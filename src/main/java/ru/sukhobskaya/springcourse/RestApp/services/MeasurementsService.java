package ru.sukhobskaya.springcourse.RestApp.services;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sukhobskaya.springcourse.RestApp.dto.MeasurementDto;
import ru.sukhobskaya.springcourse.RestApp.model.Measurement;
import ru.sukhobskaya.springcourse.RestApp.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MeasurementsService {
    MeasurementsRepository measurementsRepository;
    ModelMapper modelMapper;

    public List<MeasurementDto> findAll() {
        return measurementsRepository.findAll().stream()
                .map(measurement -> modelMapper.map(measurement, MeasurementDto.class))
                .toList();
    }

    @Transactional
    public void create(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
        measurementsRepository.save(measurement);
    }

    public int rainyDaysCount() {
        return measurementsRepository.findByIsRainyTrue().stream()
                .map(Measurement::getCreatedAt)
                .map(LocalDateTime::toLocalDate)
                .toList().size();
    }
}

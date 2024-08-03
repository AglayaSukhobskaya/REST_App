package ru.sukhobskaya.springcourse.sensor.services;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sukhobskaya.springcourse.sensor.dto.MeasurementDto;
import ru.sukhobskaya.springcourse.sensor.model.Measurement;
import ru.sukhobskaya.springcourse.sensor.repositories.MeasurementRepository;
import ru.sukhobskaya.springcourse.sensor.util.SensorValidator;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MeasurementService {
    MeasurementRepository measurementRepository;
    SensorService sensorService;
    SensorValidator sensorValidator;
    ModelMapper modelMapper;

    public List<MeasurementDto> findAll() {
        var measurements = measurementRepository.findAll();
        return measurements.stream()
                .map(measurement -> modelMapper.map(measurement, MeasurementDto.class))
                .toList();
    }

    @Transactional
    public void create(Double value, Boolean isRainy, String sensorName) {
        var sensor = sensorService.findByName(sensorName);
        sensorValidator.validateSensorExist(sensorName, sensor);
        var measurement = Measurement.builder()
                .value(value)
                .isRainy(isRainy)
                .sensor(sensor)
                .timeOfMeasurement(LocalDateTime.now())
                .build();
        measurementRepository.saveAndFlush(measurement);
    }

    public Integer countRainyDays() {
        return measurementRepository.findByIsRainyTrue().stream()
                .map(Measurement::getTimeOfMeasurement)
                .map(LocalDateTime::toLocalDate)
                .distinct()
                .toList().size();
    }
}

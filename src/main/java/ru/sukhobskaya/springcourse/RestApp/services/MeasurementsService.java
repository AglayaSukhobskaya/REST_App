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
import ru.sukhobskaya.springcourse.RestApp.util.SensorValidator;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MeasurementsService {
    MeasurementsRepository measurementsRepository;
    SensorsService sensorsService;
    SensorValidator sensorValidator;
    ModelMapper modelMapper;

    public List<MeasurementDto> findAll() {
        var measurements = measurementsRepository.findAll();
        return measurements.stream()
                .map(measurement -> modelMapper.map(measurement, MeasurementDto.class))
                .toList();
    }

    @Transactional
    public void create(Double value, Boolean isRainy, String sensorName) {
        var sensor = sensorsService.findByName(sensorName);
        sensorValidator.validateSensorExist(sensorName, sensor);
        var measurement = new Measurement();
        measurement.setValue(value);
        measurement.setIsRainy(isRainy);
        measurement.setSensor(sensor);
        measurement.setCreatedAt(LocalDateTime.now());
        measurementsRepository.saveAndFlush(measurement);
    }

    public Integer rainyDaysCount() {
        return measurementsRepository.findByIsRainyTrue().stream()
                .map(Measurement::getCreatedAt)
                .map(LocalDateTime::toLocalDate)
                .distinct()
                .toList().size();
    }
}

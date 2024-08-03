package ru.sukhobskaya.sensor.controllers;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sukhobskaya.sensor.dto.MeasurementDto;
import ru.sukhobskaya.sensor.services.MeasurementService;
import ru.sukhobskaya.sensor.util.SensorExceptionHandler;

import java.util.List;

@RestController
@RequestMapping("/measurements")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MeasurementController implements SensorExceptionHandler {
    MeasurementService measurementService;

    @GetMapping("/all")
    public List<MeasurementDto> getAll() {
        return measurementService.findAll();
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> create(@RequestParam @Min(value = -100) @Max(value = 100) Double temperature,
                                             @RequestParam Boolean isRainy,
                                             @RequestParam @Size(min = 3, max = 30) String sensorName) {
        measurementService.create(temperature, isRainy, sensorName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainy-days")
    public Integer countRainyDays() {
        return measurementService.countRainyDays();
    }
}

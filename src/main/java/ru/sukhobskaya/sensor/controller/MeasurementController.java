package ru.sukhobskaya.sensor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import ru.sukhobskaya.sensor.service.MeasurementService;
import ru.sukhobskaya.sensor.util.SensorExceptionHandler;

import java.util.List;


@RestController
@RequestMapping("/measurements")
@AllArgsConstructor
@Tag(name = "Measurement API")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MeasurementController implements SensorExceptionHandler {
    MeasurementService measurementService;

    @GetMapping("/all")
    @Operation(summary = "Get all measurements")
    public List<MeasurementDto> getAll() {
        return measurementService.findAll();
    }

    @PostMapping("/new")
    @Operation(summary = "Make new measurement")
    public ResponseEntity<HttpStatus> create(@RequestParam @Min(value = -100) @Max(value = 100)
                                             @Parameter(description = "Temperature")
                                             Double temperature,
                                             @RequestParam @Parameter(description = "Rain indicator")
                                             Boolean isRainy,
                                             @RequestParam @Size(min = 3, max = 30)
                                             @Parameter(description = "Sensor name")
                                             String sensorName) {
        measurementService.create(temperature, isRainy, sensorName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainy-days")
    @Operation(summary = "Get the total number of rainy days")
    public Integer countRainyDays() {
        return measurementService.countRainyDays();
    }
}

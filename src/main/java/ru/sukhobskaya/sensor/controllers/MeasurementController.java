package ru.sukhobskaya.sensor.controllers;

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
import ru.sukhobskaya.sensor.services.MeasurementService;
import ru.sukhobskaya.sensor.util.SensorExceptionHandler;

import java.util.List;

@Tag(name = "Measurement API")
@RestController
@RequestMapping("/measurements")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MeasurementController implements SensorExceptionHandler {
    MeasurementService measurementService;

    @Operation(summary = "Get all measurements")
    @GetMapping("/all")
    public List<MeasurementDto> getAll() {
        return measurementService.findAll();
    }

    @Operation(summary = "Make new measurement")
    @PostMapping("/new")
    public ResponseEntity<HttpStatus> create(@RequestParam @Min(value = -100) @Max(value = 100)
                                             @Parameter(description = "Temperature") Double temperature,
                                             @RequestParam @Parameter(description = "Rain indicator") Boolean isRainy,
                                             @RequestParam @Size(min = 3, max = 30)
                                             @Parameter(description = "Sensor name") String sensorName) {
        measurementService.create(temperature, isRainy, sensorName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Get the total number of rainy days")
    @GetMapping("/rainy-days")
    public Integer countRainyDays() {
        return measurementService.countRainyDays();
    }
}

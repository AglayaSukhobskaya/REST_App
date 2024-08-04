package ru.sukhobskaya.sensor.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sukhobskaya.sensor.services.SensorService;
import ru.sukhobskaya.sensor.util.SensorExceptionHandler;

import java.util.List;


@RestController
@RequestMapping("/sensors")
@AllArgsConstructor
@Tag(name = "Sensor API")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SensorController implements SensorExceptionHandler {
    SensorService sensorService;

    @Operation(summary = "Get all sensors")
    @GetMapping("/all")
    public List<String> getAll() {
        return sensorService.findAll();
    }

    @Operation(summary = "Register new sensor")
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestParam @Size(min = 3, max = 30)
                                             @Parameter(description = "Sensor name") String name) {
        sensorService.create(name);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

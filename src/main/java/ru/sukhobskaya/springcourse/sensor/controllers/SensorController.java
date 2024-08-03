package ru.sukhobskaya.springcourse.sensor.controllers;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sukhobskaya.springcourse.sensor.services.SensorService;
import ru.sukhobskaya.springcourse.sensor.util.SensorExceptionHandler;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SensorController implements SensorExceptionHandler {
    SensorService sensorService;

    @GetMapping("/all")
    public List<String> getAll() {
        return sensorService.findAll();
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestParam @Size(min = 3, max = 30) String sensorName) {
        sensorService.create(sensorName);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

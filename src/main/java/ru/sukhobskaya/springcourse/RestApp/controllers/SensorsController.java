package ru.sukhobskaya.springcourse.RestApp.controllers;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sukhobskaya.springcourse.RestApp.dto.SensorDto;
import ru.sukhobskaya.springcourse.RestApp.model.Sensor;
import ru.sukhobskaya.springcourse.RestApp.services.SensorsService;
import ru.sukhobskaya.springcourse.RestApp.util.ErrorResponse;
import ru.sukhobskaya.springcourse.RestApp.util.SensorException;
import ru.sukhobskaya.springcourse.RestApp.util.SensorNotCreatedException;

import java.time.Instant;

@RestController
@RequestMapping("/sensors")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SensorsController {
    SensorsService sensorsService;
    ModelMapper modelMapper;

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestParam @Size(min = 3, max = 30) String sensorName) {
        sensorsService.create(sensorName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorException e) {
        var response = new ErrorResponse(
                e.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedException e) {
        var response = new ErrorResponse(e.getMessage(), Instant.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDto sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}

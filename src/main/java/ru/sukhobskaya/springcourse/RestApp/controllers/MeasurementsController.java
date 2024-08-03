package ru.sukhobskaya.springcourse.RestApp.controllers;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sukhobskaya.springcourse.RestApp.dto.MeasurementDto;
import ru.sukhobskaya.springcourse.RestApp.services.MeasurementsService;
import ru.sukhobskaya.springcourse.RestApp.util.ErrorResponse;
import ru.sukhobskaya.springcourse.RestApp.util.SensorException;
import ru.sukhobskaya.springcourse.RestApp.util.MeasurementNotFoundException;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/measurements")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MeasurementsController {
    MeasurementsService measurementsService;

    @GetMapping("/all")
    public List<MeasurementDto> getAll() {
        return measurementsService.findAll();
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> create(@RequestParam @Min(value = -100) @Max(value = 100) Double value,
                                             @RequestParam Boolean isRainy,
                                             @RequestParam @Size(min = 3, max = 30) String sensorName) {
        measurementsService.create(value, isRainy, sensorName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public Integer rainyDaysCount() {
        return measurementsService.rainyDaysCount();
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotFoundException e) {
        var response = new ErrorResponse(
                "Measurement with this id was not found!",
                Instant.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorException e) {
        var response = new ErrorResponse(
                e.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

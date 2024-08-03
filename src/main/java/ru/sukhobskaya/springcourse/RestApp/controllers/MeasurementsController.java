package ru.sukhobskaya.springcourse.RestApp.controllers;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.sukhobskaya.springcourse.RestApp.dto.MeasurementDto;
import ru.sukhobskaya.springcourse.RestApp.model.Measurement;
import ru.sukhobskaya.springcourse.RestApp.services.MeasurementsService;
import ru.sukhobskaya.springcourse.RestApp.services.SensorsService;
import ru.sukhobskaya.springcourse.RestApp.util.ErrorResponse;
import ru.sukhobskaya.springcourse.RestApp.util.MeasurementNotCreatedException;
import ru.sukhobskaya.springcourse.RestApp.util.MeasurementNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/measurements")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MeasurementsController {
    MeasurementsService measurementsService;
    SensorsService sensorsService;
    ModelMapper modelMapper;

    @GetMapping
    public List<MeasurementDto> getAll() {
        return measurementsService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDto measurementDTO,
                                             BindingResult bindingResult) {
        var sensor = sensorsService.findByName(measurementDTO.getSensor().getName());
        if (sensor == null) {
            throw new MeasurementNotCreatedException("Sensor with this name does not exist!");
        }
        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);
        measurement.setSensor(sensor);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage()).append(";");
            }
            throw new MeasurementNotCreatedException(errorMsg.toString());
        }

        measurementsService.create(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public Integer rainyDaysCount() {
        return measurementsService.rainyDaysCount();
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                "Measurement with this id was not found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

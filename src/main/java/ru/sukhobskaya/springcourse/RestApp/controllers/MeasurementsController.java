package ru.sukhobskaya.springcourse.RestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.sukhobskaya.springcourse.RestApp.dto.MeasurementDTO;
import ru.sukhobskaya.springcourse.RestApp.models.Measurement;
import ru.sukhobskaya.springcourse.RestApp.models.Sensor;
import ru.sukhobskaya.springcourse.RestApp.services.MeasurementsService;
import ru.sukhobskaya.springcourse.RestApp.services.SensorsService;
import ru.sukhobskaya.springcourse.RestApp.util.ErrorResponse;
import ru.sukhobskaya.springcourse.RestApp.util.MeasurementNotCreatedException;
import ru.sukhobskaya.springcourse.RestApp.util.MeasurementNotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, SensorsService sensorsService, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<MeasurementDTO> getMeasurements() {
        return measurementsService.findAll().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO,
                                             BindingResult bindingResult) {
        Sensor sensor = sensorsService.findByName(measurementDTO.getSensor().getName());
        if (sensor == null)
            throw new MeasurementNotCreatedException("Sensor with this name does not exist!");
        Measurement measurement = convertToMeasurement(measurementDTO);
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

        measurementsService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public int rainyDaysCount() {
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

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}

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
import ru.sukhobskaya.springcourse.RestApp.dto.SensorDto;
import ru.sukhobskaya.springcourse.RestApp.model.Sensor;
import ru.sukhobskaya.springcourse.RestApp.services.SensorsService;
import ru.sukhobskaya.springcourse.RestApp.util.ErrorResponse;
import ru.sukhobskaya.springcourse.RestApp.util.SensorNotCreatedException;
import ru.sukhobskaya.springcourse.RestApp.util.SensorValidator;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SensorsController {
    SensorsService sensorsService;
    SensorValidator sensorValidator;
    ModelMapper modelMapper;

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDto sensorDTO,
                                             BindingResult bindingResult) {
        var sensor = convertToSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);

        if (bindingResult.hasErrors()) {
            var errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage()).append(";");
            }
            throw new SensorNotCreatedException(errorMsg.toString());
        }
        sensorsService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedException e) {
        var response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDto sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}

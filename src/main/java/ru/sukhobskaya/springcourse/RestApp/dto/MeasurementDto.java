package ru.sukhobskaya.springcourse.RestApp.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class MeasurementDto {
    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value should be greater than -100")
    @Max(value = 100, message = "Value should be less than 100")
    private Double value;

    @NotNull(message = "Raining should not be empty")
    private Boolean isRainy;

    @NotNull
    private SensorDto sensor;
}

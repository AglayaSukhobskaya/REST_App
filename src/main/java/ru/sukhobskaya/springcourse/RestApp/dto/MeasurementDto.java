package ru.sukhobskaya.springcourse.RestApp.dto;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeasurementDto {
    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value should be greater than -100")
    @Max(value = 100, message = "Value should be less than 100")
    Double value;

    @NotNull(message = "Raining should not be empty")
    Boolean isRainy;

    @NotNull
    SensorDto sensor;
}

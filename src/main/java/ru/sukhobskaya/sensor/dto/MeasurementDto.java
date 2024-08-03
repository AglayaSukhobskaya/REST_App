package ru.sukhobskaya.sensor.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeasurementDto {
    Double temperature;
    Boolean isRainy;
    String sensor;
}

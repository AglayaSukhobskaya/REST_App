package ru.sukhobskaya.sensor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Sensor measurement")
public class MeasurementDto {
    @Schema(description = "Air temperature")
    Double temperature;
    @Schema(description = "Rain indicator")
    Boolean isRainy;
    @Schema(description = "Sensor name")
    String sensor;
}

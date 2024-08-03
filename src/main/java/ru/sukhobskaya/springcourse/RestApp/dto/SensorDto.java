package ru.sukhobskaya.springcourse.RestApp.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SensorDto {
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    @NotEmpty(message = "Name should not be empty")
    private String name;
}

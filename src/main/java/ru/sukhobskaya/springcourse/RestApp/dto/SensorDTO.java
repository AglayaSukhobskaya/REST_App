package ru.sukhobskaya.springcourse.RestApp.dto;

import jakarta.validation.constraints.*;

public class SensorDTO {
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

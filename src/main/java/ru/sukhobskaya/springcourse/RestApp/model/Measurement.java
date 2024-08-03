package ru.sukhobskaya.springcourse.RestApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Measurement")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "value")
    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value should be greater than -100")
    @Max(value = 100, message = "Value should be less than 100")
    Double value;

    @Column(name = "is_rainy")
    @NotNull(message = "Raining should not be empty")
    Boolean isRainy;

    @ManyToOne
    @NotNull(message = "Sensor should not be empty")
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    Sensor sensor;

    @Column(name = "created_at")
    LocalDateTime createdAt;
}

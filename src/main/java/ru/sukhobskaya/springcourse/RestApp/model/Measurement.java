package ru.sukhobskaya.springcourse.RestApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value should be greater than -100")
    @Max(value = 100, message = "Value should be less than 100")
    private Double value;

    @Column(name = "raining")
    @NotNull(message = "Raining should not be empty")
    private Boolean raining;

    @ManyToOne
    @NotNull(message = "Sensor should not be empty")
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Measurement() {}

    public Measurement(double value, boolean raining) {
        this.value = value;
        this.raining = raining;
    }

}

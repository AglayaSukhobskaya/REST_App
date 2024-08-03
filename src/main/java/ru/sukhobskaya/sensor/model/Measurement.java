package ru.sukhobskaya.sensor.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Measurement")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "value", nullable = false)
    Double value;

    @Column(name = "is_rainy", nullable = false)
    Boolean isRainy;

    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name", nullable = false)
    Sensor sensor;

    @Column(name = "time_of_measurement")
    LocalDateTime timeOfMeasurement;
}

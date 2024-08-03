package ru.sukhobskaya.springcourse.RestApp.model;

import jakarta.persistence.*;
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

    @Column(name = "value", nullable = false)
    Double value;

    @Column(name = "is_rainy", nullable = false)
    Boolean isRainy;

    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name", nullable = false)
    Sensor sensor;

    @Column(name = "created_at")
    LocalDateTime createdAt;
}

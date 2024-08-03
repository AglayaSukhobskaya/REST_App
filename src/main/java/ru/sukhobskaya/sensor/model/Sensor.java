package ru.sukhobskaya.sensor.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Sensor")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sensor implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name", nullable = false, length = 30, unique = true)
    String name;

    @Override
    public String toString() {
        return name;
    }
}

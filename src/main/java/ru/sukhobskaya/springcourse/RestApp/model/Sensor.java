package ru.sukhobskaya.springcourse.RestApp.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Entity
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

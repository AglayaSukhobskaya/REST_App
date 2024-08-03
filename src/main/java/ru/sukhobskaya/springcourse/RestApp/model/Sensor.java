package ru.sukhobskaya.springcourse.RestApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @Column(name = "name")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    @NotEmpty(message = "Name should not be empty")
    String name;
}

package ru.sukhobskaya.sensor.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Weather measurement API",
                description = "Sensor simulator", version = "1.0.0",
                contact = @Contact(
                        name = "Sukhobskaia Aglaia",
                        email = "a.sukhobskaya@mail.ru",
                        url = "https://github.com/AglayaSukhobskaya"
                )
        )
)
public class SwaggerConfig {
}

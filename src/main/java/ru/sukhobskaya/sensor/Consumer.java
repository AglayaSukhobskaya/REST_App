package ru.sukhobskaya.sensor;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Consumer {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        String sensorName = "Test_name2";

//      New sensor registration:
        System.out.println(registerSensor(sensorName));

//      Add 1000 random measurements:
        Random random = new Random();
        double value;
        boolean raining;
        for (int i = 0; i < 1000; i++) {
            value = Math.round((Math.random() * 200 - 100) * 100) / 100.0;
            raining = random.nextBoolean();
            System.out.println(addMeasurement(value, raining, sensorName));
        }

//      Get all measurements:
        System.out.println(getMeasurements());
    }

    private static String registerSensor(String sensorName) {
        String url = "http://localhost:8080/sensors/registration";

        Map<String, Object> sensorJson = new HashMap<>();
        sensorJson.put("name", sensorName);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(sensorJson);
        return restTemplate.postForObject(url, request, String.class);
    }

    private static String addMeasurement(double value, boolean raining, String sensorName) {
        String url = "http://localhost:8080/measurements/add";

        Map<String, Object> sensor = new HashMap<>();
        sensor.put("name", sensorName);

        Map<String, Object> measurementJson = new HashMap<>();
        measurementJson.put("value", value);
        measurementJson.put("raining", raining);
        measurementJson.put("sensor", sensor);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(measurementJson);
        return restTemplate.postForObject(url, measurementJson, String.class);
    }

    private static String getMeasurements() {
        String url = "http://localhost:8080/measurements";
        return restTemplate.getForObject(url, String.class);
    }

}

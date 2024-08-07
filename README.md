Sensor measurements API
========
This application consists of two parts:
+ **REST API service** that registers meteorological sensors, receives data from them and stores them in a database. Sensors measure the air temperature and determine whether it is raining or not. The service can count the number of rainy days.
+ **Сonsumer** that registers a new sensor, performs 1000 random measurements and sends them to the REST API service, receives all available measurements from the service.

Running the application
========
System requirments: you must have docker and docker compose installed on your computer.

Open Terminal.

Change the current working directory to the location where you want the cloned the project directory.
 
Enter the following command to clone the project to the selected directory:

    git clone https://github.com/AglayaSukhobskaya/sensor_measurements.git

Go to the project root directory 'sensor_measurements'.

Run the application with the following command:

    sudo docker compose up -d

Now the application is running.

Follow the link:

http://localhost:8080/swagger-ui/index.html

Technology Stack
========
Java 17, Spring Boot 3.3.2, REST API, Spring Data JPA, PostgreSQL



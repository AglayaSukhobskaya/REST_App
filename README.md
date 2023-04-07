# sensor_measurements
This application consists of two parts:
+ **REST API service** that registers meteorological sensors, receives data from them and stores them in a database. Sensors measure the air temperature and determine whether it is raining or not. The service can count the number of rainy days.
+ **Сonsumer** that registers a new sensor, performs 1000 random measurements and sends them to the REST API service, receives all available measurements from the service.

## Getting Started
The following programs must be installed on your computer:
 Java Development Kit (JDK);
 development environment (IDE) such as Eclipse, IntelliJ IDEA or another;
 Apache Maven;
 PostgreSQL database.

Clone this repository to your local computer:

<code>git clone https://github.com/AglayaSukhobskaya/sensor_measurements</code>

Open the project in the development environment.
Download all the necessary sources and documentation from Maven.

Create a new PostgreSQL database and implement a connection to the service. All connection settings you can find in ***resources/application.properties***.
To create the necessary tables in the database execute the SQL queries from file ***resaurces/db.sql***.

Now application ready for running.

## Running the application
First you need to run REST API service: method main() from class ***ru.sukhobskaya.springcourse.RestApp.RestAppApplication***.

After that run the Consumer: method main() from class ***ru.sukhobskaya.springcourse.RestApp.Consumer***.

If everything is done correctly, you will see in the console a report on the registration of a new sensor, a report on adding 1000 random measurements to the database as well as the values of all measurements.

## Technology Stack
Spring Boot / Spring REST / Hibernate Validator / PostgreSQL / RestTemplate



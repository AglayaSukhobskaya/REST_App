# sensor_measurements
This application consists of two parts:
+ REST API service that registers meteorological sensors, receives data from them and stores them in a database. Sensors measure the air temperature and determine whether it is raining or not. The service can count the number of rainy days.
+ Сonsumer that registers a new sensor, performs 1000 random measurements and sends them to the REST API service, receives all available measurements from the service.


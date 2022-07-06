# Rest Api Sensor

___
Проект состоит и двух приложений RestApiService и SensorClient. RestApiService принимает данные от метеорологического
датчика (сенсор). SensorClient измеряет температуру воздуха, определяет идет дождь или нет и отправляет данные.
___

# Функционал REST API

Принимает данные от сенсора и хранит БД.

* POST запрос http://localhost:8080/sensors/registration. Регистрирует сенсор в системе.
* POST запрос http://localhost:8080/measurements/add. Отправляет данные измерения на сервер.
* GET запрос http://localhost:8080/measurements. Получает данные всех измерений.
* GET запрос http://localhost:8080/measurements/rainyDaysCount. Получается количество дождливых дней.

___

# Функционал REST API

Измеряет температуру воздуха и определяет идет дождь или нет. Строит график температур.

---

# Стэк проекта

* Spring Boot;
* Spring Validator;
* Spring Data;
* Hibernate;
* PostgreSQL;
* Xchart;

___

# Запуск приложения

* Откройте среду разработки IDE, в проекте RestApi откройте файл application.properties и измените URL-адрес источника
  данных spring, имя пользователя и пароль.
* Прямо сейчас URL-адрес источника данных spring = "jdbc:postgresql://localhost:5432/rest_api", имя = "postgres", пароль
  = "2400024".
* Запустите проект RestApi и SensorClient.
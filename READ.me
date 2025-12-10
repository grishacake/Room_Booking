# Room Booking (Резервирование переговорок)

Лабораторная работа по Java / Spring Boot.  
Тема: **Резервирование переговорок**.

Основной функционал:

- управление переговорными комнатами (Room);
- управление оборудованием (Equipment);
- бронирование переговорок (Booking) с проверкой пересечений;
- просмотр расписания комнаты;
- базовая валидация данных и обработка ошибок;
- примитивные unit-тесты сервиса бронирования.

---

## 1. Стек технологий

- Java 17
- Spring Boot 3
  - Spring Web (REST API)
  - Spring Data JPA / Hibernate
  - Bean Validation (Jakarta Validation)
- База данных:
  - по умолчанию: H2
- Сборка: Maven
- Тестирование: JUnit 5, Mockito

---

## 2. Архитектура и сущности

## 2. Архитектура и пакеты

Корень проекта:

```text
room-booking/
  pom.xml
  src/
    main/
      java/com/example/roombooking/
        RoomBookingApplication.java
        config/
          GlobalExceptionHandler.java
        model/
          Room.java
          Equipment.java
          Booking.java
        room/
          RoomController.java
          RoomRepository.java
          RoomResponse.java
        booking/
          BookingController.java
          BookingService.java
          BookingRepository.java
          BookingRequest.java
          BookingResponse.java
          BookingConflictException.java
        equipment/
          EquipmentRepository.java
      resources/
        application.yml
    test/
      java/com/example/roombooking/booking/
        BookingServiceTest.java

---

## 3. Валидация и обработка ошибок

Используются аннотации Jakarta Validation:

- `@NotBlank`, `@NotNull`, `@Min` на полях сущностей и DTO.
- `@Valid` в контроллере при приёме `BookingRequest`.

Обработчик ошибок `GlobalExceptionHandler`:

- `MethodArgumentNotValidException` → `400 Bad Request` + список ошибок по полям;
- `BookingConflictException` → `409 Conflict` + сообщение о конфликте бронирования;
- `IllegalArgumentException` → `400 Bad Request` (например, комната не найдена, неверный интервал);
- общий `Exception` → `500 Internal Server Error`.

---

## 4. База данных и инициализация (H2)

Конфигурация в `src/main/resources/application.yml`:

- in-memory база `jdbc:h2:mem:roombooking`;
- режим `MODE=PostgreSQL` для большей совместимости;
- авто-генерация схемы `spring.jpa.hibernate.ddl-auto=update`;
- H2 console: `http://localhost:8080/h2-console`.

При запуске приложения выполняется `CommandLineRunner` в `RoomBookingApplication`:

- создаёт несколько записей в `equipment`;
- создаёт 3 комнаты (Small / Medium / Big) с разным набором оборудования;
- всё это делает демонстрацию проще: сразу есть данные для запросов.

## 5. Запуск

```bash
mvn clean test
mvn spring-boot:run
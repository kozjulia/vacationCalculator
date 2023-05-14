package ru.vacationCalculator.controller;

import ru.vacationCalculator.service.CalculateService;
import ru.vacationCalculator.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateControllerTest {
    private CalculateService service;
    private CalculateController controller;

    @BeforeEach
    void setUp() {
        service = new CalculateService();
        controller = new CalculateController(service);
    }

    @Test
    @DisplayName("тест расчёта отпускных")
    void calculateFrom100000And9To2559o69() {
        Double result = controller.calculate(100000.00, 9);
        assertEquals(2559.69, result, "Неверный расчёт отпускных.");
    }

    @Test
    @DisplayName("тест с неправильной средней зарплатой за 12 месяцев")
    void calculateFailAverageSalary() {
        final ValidationException exception = assertThrows(
                ValidationException.class,
                () -> controller.calculate(-1000.88, 10));
        assertEquals("Ошибка! Твоя средняя зарплата за 12 месяцев должна быть положительной.",
                exception.getMessage());
    }

    @Test
    @DisplayName("тест с неправильным количеством дней отпуска")
    void calculateFailVacationDays() {
        final ValidationException exception = assertThrows(
                ValidationException.class,
                () -> controller.calculate(870000.00, -5));
        assertEquals("Ошибка! Количество дней отпуска должно быть положительным.",
                exception.getMessage());
    }

}
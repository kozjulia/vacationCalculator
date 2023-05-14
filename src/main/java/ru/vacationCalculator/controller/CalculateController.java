package ru.vacationCalculator.controller;

import ru.vacationCalculator.exception.ValidationException;
import ru.vacationCalculator.service.CalculateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CalculateController {
    private final CalculateService calculateService;

    @GetMapping(path = {"/calculacte", "/calculate"})
    // эндпойнт в задании с опечаткой, на всякий случай 2 варианта рассматриваю
    public Double calculate(@RequestParam Double averageSalary, @RequestParam Integer vacationDays) {
        if (averageSalary < 0) {
            String message = "Ошибка! Твоя средняя зарплата за 12 месяцев должна быть положительной.";
            log.warn(message);
            throw new ValidationException(message);
        }
        if (vacationDays < 0) {
            String message = "Ошибка! Количество дней отпуска должно быть положительным.";
            log.warn(message);
            throw new ValidationException(message);
        }
        log.debug("Твоя средняя зарплата за 12 месяцев: {}, количество дней отпуска {}", averageSalary, vacationDays);
        Double result = calculateService.calculate(averageSalary, vacationDays);
        log.debug("Твои отпускные равны {}", result);
        return result;
    }

}
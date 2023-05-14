package ru.vacationCalculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculateService {
    public Double calculate(Double averageSalary, Integer vacationDays) {
        // расчёт отпускных
        String dailySalaryString = String.format("%.2f", averageSalary / 12 / 29.3).replace(",", ".");
        Double dailySalary = Double.parseDouble(dailySalaryString);
        return dailySalary * vacationDays;
    }

}
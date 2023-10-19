package ru.aurakhov.mysecondtestapspringbootlr5.service;

import org.springframework.stereotype.Service;
import ru.aurakhov.mysecondtestapspringbootlr5.model.Positions;

import java.time.Year;
import java.util.Calendar;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDay) {
        return salary * bonus * daysInYear() * positions.getPositionCoefficient() / workDay;
    }

    private int daysInYear() {
        Calendar calendar = Calendar.getInstance();
        int yearNumber = calendar.get(Calendar.YEAR); // Задайте нужный год
        Year year = Year.of(yearNumber);

        int numberOfDays = year.length();
        return numberOfDays;
    }
}

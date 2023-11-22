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

    public double quarterlyBonusCalculation(Positions positions, double salary, double bonus, int workDay) {

        if (positions.isManager()) {
            System.out.println("isManager = true");
            double result = salary * bonus * daysInQuarter() * positions.getPositionCoefficient() / workDay;
            System.out.println("Квартальная премия для " + positions + " = " + result);
            return result;


        }
        System.out.println("isManager = false\n" +
                "квартальная премия не предусмотрена для: " + positions);
        return 0.0;
    }

    private int daysInYear() {
        Calendar calendar = Calendar.getInstance();
        int yearNumber = calendar.get(Calendar.YEAR); // Задайте нужный год
        Year year = Year.of(yearNumber);

        int numberOfDays = year.length();
        return numberOfDays;
    }

    private int daysInQuarter() {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        int daysInQuarter = 0;

        // Определяем количество дней в каждом месяце квартала
        for (int i = 0; i < 3; i++) {
            // Устанавливаем текущий месяц
            calendar.set(Calendar.MONTH, currentMonth + i);
            // Получаем количество дней в текущем месяце
            int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            // Суммируем количество дней
            daysInQuarter += daysInMonth;
        }
        System.out.println("количество дней в квартале: " + daysInQuarter);
        return daysInQuarter;
    }
}

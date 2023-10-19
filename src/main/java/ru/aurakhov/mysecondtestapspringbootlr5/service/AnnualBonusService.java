package ru.aurakhov.mysecondtestapspringbootlr5.service;

import org.springframework.stereotype.Service;
import ru.aurakhov.mysecondtestapspringbootlr5.model.Positions;

@Service
public interface AnnualBonusService {

    double calculate(Positions positions, double salary, double bonus, int workDay);
}

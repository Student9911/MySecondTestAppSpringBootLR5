package ru.aurakhov.mysecondtestapspringbootlr5.service;

import org.junit.jupiter.api.Test;
import ru.aurakhov.mysecondtestapspringbootlr5.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {

        //given
        Positions positions = Positions.HR;
        double bonus = 2.0;
        int workDay = 60;
        double salary = 100000.00;

        //when
        double result = new AnnualBonusServiceImpl().calculate(positions, salary, bonus, workDay);



        //then
        double expected = 360493.8271604938;
        assertThat(result).isEqualTo(expected);
    }



    @Test
    void testQuarterlyBonusCalculation() {

        //given
        Positions positions = Positions.IT;
        double bonus = 2.0;
        int workDay = 60;
        double salary = 100000.00;

        //when
        double result = new AnnualBonusServiceImpl().quarterlyBonusCalculation(positions, salary, bonus, workDay);



        //then
        double expected = 828000.0;
        assertThat(result).isEqualTo(expected);
    }
}
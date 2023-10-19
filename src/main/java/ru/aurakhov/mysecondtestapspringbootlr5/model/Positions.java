package ru.aurakhov.mysecondtestapspringbootlr5.model;

import lombok.Getter;

@Getter
public enum Positions {


    DEV(2.2, false),
    HR(1.2, false),
    TL(2.6, false),
    IT(1.7, false),
    PR(2.3, false),
    CEO(3.0, true),
    MGMT(2.7, true);

    private final double positionCoefficient;
    private final boolean isManager;

    Positions(double positionCoefficient, boolean isManager) {
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }



}

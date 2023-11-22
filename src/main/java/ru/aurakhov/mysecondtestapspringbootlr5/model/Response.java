package ru.aurakhov.mysecondtestapspringbootlr5.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    // Уникальный идентификатор сообщения
    private String uid;

    // Уникальный идентификатор операции
    private String operationUid;

    //Имя системы отправителя
    private SystemsName systemsName;

    // Время создания сообщения
    private String systemTime;

    // статус
    private Codes code;

    // расчетная премия
    private Double annualBonus;

    // код ошибки
    private ErrorCodes errorCode;

    // сообщение об ошибке
    private ErrorMessages errorMessage;
}

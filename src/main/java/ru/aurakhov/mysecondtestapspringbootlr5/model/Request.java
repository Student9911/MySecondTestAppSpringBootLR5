package ru.aurakhov.mysecondtestapspringbootlr5.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @NotBlank(message = "UID не может быть пустым")
    private String uid;

    private String operationUid;
    private String systemName;

    private String systemTime;
    private String source;

    private Positions positions;
    private Double salary;

    private Double bonus;
    private Integer workDay;

    @Range(min = 1, max = 100000,
            message = "Значение должно быть в диапазоне от 1 до 100000")
    private int communicationId;
    private int templateId;
    private int productCode;
    private int smsCode;

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId='" + communicationId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", productCode='" + productCode + '\'' +
                ", smsCode='" + smsCode +
                '}';
    }

}




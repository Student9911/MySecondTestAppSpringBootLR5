package ru.aurakhov.mysecondtestapspringbootlr5.service;

import lombok.extern.slf4j.Slf4j;
import ru.aurakhov.mysecondtestapspringbootlr5.model.Request;
import ru.aurakhov.mysecondtestapspringbootlr5.model.Response;
import ru.aurakhov.mysecondtestapspringbootlr5.model.SystemsName;

@Slf4j
public class ModifySystemNameResponseService implements ModifyResponseService {
    Request request;
    @Override
    public Response modify(Response response) {

        switch (response.getSystemsName()) {
            case WMS -> response.setSystemsName(SystemsName.WMS);
            case ERP -> response.setSystemsName(SystemsName.ERP);
            case CRM -> response.setSystemsName(SystemsName.CRM);

        }
        log.info("Здесь проверяется какое SystemName и в зависимости от аббревиатуры " +
                "присваивается значения из класса перечисления: " + response.getOperationUid());
        return response;
    }
}

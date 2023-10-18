package ru.aurakhov.mysecondtestapspringbootlr5.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.aurakhov.mysecondtestapspringbootlr5.model.Response;

import java.util.UUID;

@Slf4j
@Service
@Component
public class ModifyOperationUidResponseService implements ModifyResponseService {
    @Override
    public Response modify(Response response) {

        UUID uuid = UUID.randomUUID();

        response.setOperationUid(uuid.toString());
        log.info("Класс modifyOperationUidResponseService" +
                "OperationUid присваивается рандомный ID: "+ response.getOperationUid());

        return response;
    }
}

package ru.aurakhov.mysecondtestapspringbootlr5.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.aurakhov.mysecondtestapspringbootlr5.model.Request;

@Slf4j
@Service
@Component
public class ModifySourceRequestService implements ModifyRequestService{
    @Override
    public void modify(Request request) {

        request.setSource("ну допустим поменяли значение");

    }
}

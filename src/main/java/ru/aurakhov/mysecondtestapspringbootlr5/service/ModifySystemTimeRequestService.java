package ru.aurakhov.mysecondtestapspringbootlr5.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.aurakhov.mysecondtestapspringbootlr5.model.Request;
import ru.aurakhov.mysecondtestapspringbootlr5.util.DateTimeUtil;

import java.util.Date;

@Service
@Component
public class ModifySystemTimeRequestService implements ModifyRequestService{
    @Override
    public void modify(Request request) {
        request.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));

    }
}

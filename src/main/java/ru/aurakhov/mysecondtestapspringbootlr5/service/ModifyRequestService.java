package ru.aurakhov.mysecondtestapspringbootlr5.service;

import org.springframework.stereotype.Service;
import ru.aurakhov.mysecondtestapspringbootlr5.model.Request;

@Service
public interface ModifyRequestService {

    void modify(Request request);
}

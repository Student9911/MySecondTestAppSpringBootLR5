package ru.aurakhov.mysecondtestapspringbootlr5.service;

import org.springframework.stereotype.Service;
import ru.aurakhov.mysecondtestapspringbootlr5.model.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);



}


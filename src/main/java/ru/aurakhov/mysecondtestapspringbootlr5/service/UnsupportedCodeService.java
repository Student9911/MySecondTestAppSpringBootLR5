package ru.aurakhov.mysecondtestapspringbootlr5.service;

import org.springframework.stereotype.Service;
import ru.aurakhov.mysecondtestapspringbootlr5.exception.UnsupportedCodeException;

@Service
public interface UnsupportedCodeService {

    void isValid(String s) throws UnsupportedCodeException;
}



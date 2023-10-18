package ru.aurakhov.mysecondtestapspringbootlr5.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.aurakhov.mysecondtestapspringbootlr5.exception.ValidationFailedException;

@Slf4j
@Service
public class RequestValidationService implements ValidationService {
    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            log.warn("Сообщение из bindingResult: " + bindingResult.getFieldError().getDefaultMessage());

            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }
}

            




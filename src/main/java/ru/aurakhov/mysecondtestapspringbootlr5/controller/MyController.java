package ru.aurakhov.mysecondtestapspringbootlr5.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.aurakhov.mysecondtestapspringbootlr5.exception.UnsupportedCodeException;
import ru.aurakhov.mysecondtestapspringbootlr5.exception.ValidationFailedException;
import ru.aurakhov.mysecondtestapspringbootlr5.model.*;
import ru.aurakhov.mysecondtestapspringbootlr5.service.*;
import ru.aurakhov.mysecondtestapspringbootlr5.util.DateTimeUtil;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final UnsupportedCodeService unsupportedCodeService;
    private final List<ModifyResponseService> modifyResponseService;
    private final List<ModifyRequestService> modifyRequestService;

    @Autowired
    public MyController(ValidationService validationService,
                        UnsupportedCodeService unsupportedCodeService,
                        List<ModifyResponseService> modifyResponseService,
                        List<ModifyRequestService> modifyRequestService) {
        this.validationService = validationService;
        this.unsupportedCodeService = unsupportedCodeService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;

    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {
        log.info("request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid("")
                .systemsName(SystemsName.valueOf(request.getSystemName()))
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        try {
            unsupportedCodeService.isValid(request.getUid());
        } catch (UnsupportedCodeException e) {
            return handleException(response, Codes.FAILED, ErrorCodes.UNSUPPORTED_EXCEPTION,
                    ErrorMessages.UNSUPPORTED, HttpStatus.BAD_REQUEST);
        }

        try {
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            return handleException(response, Codes.FAILED, ErrorCodes.VALIDATION_EXCEPTION,
                    ErrorMessages.VALIDATION, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return handleException(response, Codes.FAILED, ErrorCodes.UNKNOWN_EXCEPTION,
                    ErrorMessages.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifyResponse(response);
        modifyRequest(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<Response> handleException(Response response, Codes code, ErrorCodes errorCode,
                                                     ErrorMessages errorMessage, HttpStatus status) {
        response.setCode(code);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        return new ResponseEntity<>(response, status);
    }

    private void modifyResponse(Response response) {
        for (ModifyResponseService service : modifyResponseService) {
            service.modify(response);
        }
    }

    private void modifyRequest(Request request) {
        for (ModifyRequestService service : modifyRequestService) {
            service.modify(request);
        }
    }
}



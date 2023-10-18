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
import ru.aurakhov.mysecondtestapspringbootlr5.service.ModifyRequestService;
import ru.aurakhov.mysecondtestapspringbootlr5.service.ModifyResponseService;
import ru.aurakhov.mysecondtestapspringbootlr5.service.UnsupportedCodeService;
import ru.aurakhov.mysecondtestapspringbootlr5.service.ValidationService;
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
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        for (ModifyResponseService servResp : modifyResponseService) {
            servResp.modify(response);
        }
        for (ModifyRequestService serv : modifyRequestService) {
            serv.modify(request);
        }


        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

package com.texo.assessment.config;

import com.texo.assessment.dto.ErrorHandlerDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionServiceHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorHandlerDto handlerBadRequest(HttpServletRequest req, Exception ex) {
        return ErrorHandlerDto.builder()
                .code(0)
                .message(ex.getMessage())
                .build();
    }

}

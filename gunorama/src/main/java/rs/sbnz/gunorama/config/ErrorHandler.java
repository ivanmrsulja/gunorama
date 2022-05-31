package rs.sbnz.gunorama.config;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rs.sbnz.gunorama.exception.NotFoundException;
import rs.sbnz.gunorama.util.ErrorObject;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    ErrorObject handleNotFoundException(HttpServletRequest request, NotFoundException ex) {
        return new ErrorObject(request, ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

package rs.sbnz.gunorama.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rs.sbnz.gunorama.exception.UserIsBannedException;
import rs.sbnz.gunorama.util.ErrorInfo;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserIsBannedException.class)
    @ResponseBody
    public ErrorInfo handleBusinessException(HttpServletRequest request, UserIsBannedException ex) {
        return new ErrorInfo(request.getRequestURI(), ex.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ErrorInfo handleBusinessException(HttpServletRequest request, RuntimeException ex) {
        return new ErrorInfo(request.getRequestURI(), ex.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST);
    }
}

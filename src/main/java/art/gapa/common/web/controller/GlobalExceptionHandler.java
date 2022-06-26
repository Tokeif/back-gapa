package art.gapa.common.web.controller;

import art.gapa.common.web.R;
import art.gapa.common.web.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author JoverZhang
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public R<Void> exception(Exception ex) {
        log.error("{}", ex.getMessage(), ex);
        return R.error(ex.getMessage());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public R<Void> unauthorizedException(UnauthorizedException ex) {
        log.error("{}", ex.getMessage(), ex);
        return R.error(ex.getMessage());
    }

}

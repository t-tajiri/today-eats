package ttajiri.todayeats.controller.exception;

import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.*;

@ControllerAdvice
public class BackendExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger log = LoggerFactory.getLogger(BackendExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex) {
        log.error("エラー発生が発生しました。 原因: {}", ex.getMessage());
        log.error("エラー内容: {0}", ex.getCause());

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

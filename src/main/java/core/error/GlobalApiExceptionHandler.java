package core.error;

import core.error.ex.ExceptionApi400;
import core.error.ex.ExceptionApi404;
import core.util.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalApiExceptionHandler {
    
    // 유효성 검사 실패 (잘못된 클라이언트의 요청)
    @ExceptionHandler(ExceptionApi400.class)
    public ResponseEntity<?> handle400(ExceptionApi400 e) {
        return new ResponseEntity<>(Map.of("reason", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // 서버에서 리소스(자원) 찾을 수 없을때
    @ExceptionHandler(ExceptionApi404.class)
    public ResponseEntity<?> handle404(ExceptionApi404 e) {
        return new ResponseEntity<>(Map.of("reason", e.getMessage()), HttpStatus.NOT_FOUND);
    }
}

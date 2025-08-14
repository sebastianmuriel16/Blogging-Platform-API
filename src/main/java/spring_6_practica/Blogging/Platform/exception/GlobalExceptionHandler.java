package spring_6_practica.Blogging.Platform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidationException(MethodArgumentNotValidException ex){

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": "+ error.getDefaultMessage())
                .toList();

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("erros", errors);

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String,String>> handleCustomException(CustomException ex){

        Map<String,String> error = new HashMap<>();
        error.put("Error",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.valueOf(ex.getStatusCode()));

    }
}

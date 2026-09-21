package dev.fiyin.opshub.api;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class) ResponseEntity<Map<String,String>> missing(NoSuchElementException e){return ResponseEntity.status(404).body(Map.of("error",e.getMessage()));}
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Map<String,String>> invalid(MethodArgumentNotValidException e){String m=e.getBindingResult().getFieldErrors().stream().findFirst().map(x->x.getField()+" "+x.getDefaultMessage()).orElse("Invalid request");return ResponseEntity.badRequest().body(Map.of("error",m));}
}

package co.istad.chhaya.webmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice  // tell spring about exception handler
public class AppGlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<RestErrorResponse> handleService(ResponseStatusException e){
        RestErrorResponse restErrorResponse = RestErrorResponse.builder()
                .message("Business Logic error, please check")
                .code(e.getStatusCode().value())
                .status(e.getStatusCode().toString())
                .timestamp(Instant.now())
                .build();

        return new ResponseEntity<>(restErrorResponse, e.getStatusCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // set Http status 400
    @ExceptionHandler(MethodArgumentNotValidException.class)  // catch error when validation
    public RestErrorResponse handleValidation(MethodArgumentNotValidException e){
        List<FieldResponse> fields = new ArrayList<>();

        e.getFieldErrors().forEach(fieldError -> {
            fields.add(new FieldResponse(fieldError.getField()
                    , fieldError.getDefaultMessage()));
        });

        return RestErrorResponse.builder()
                .message("Request data is invalid")
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .timestamp(Instant.now())
                .errorDetail(fields).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // set Http status 400
    @ExceptionHandler(HttpMessageNotReadableException.class)  // catch error when validation
    public RestErrorResponse handleInvalidJson(HttpMessageNotReadableException e){

        return RestErrorResponse.builder()
                .message("Request data is invalid")
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .timestamp(Instant.now())
                .errorDetail(e.getMessage())
                .build();
    }
}

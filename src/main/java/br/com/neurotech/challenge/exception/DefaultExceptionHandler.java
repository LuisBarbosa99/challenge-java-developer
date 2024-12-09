package br.com.neurotech.challenge.exception;

import br.com.neurotech.challenge.entity.dto.ErrorDTO;
import br.com.neurotech.challenge.entity.dto.FieldErrorDTO;
import br.com.neurotech.challenge.entity.dto.FormErrorDTO;
import br.com.neurotech.challenge.entity.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static br.com.neurotech.challenge.entity.util.Constants.ErrorCodes;
import static br.com.neurotech.challenge.entity.util.Constants.ErrorMessages;

@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FormErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(item -> new FieldErrorDTO(item.getField(), item.getDefaultMessage()))
                .toList();
        return ResponseEntity.badRequest()
                .body(new FormErrorDTO(ErrorCodes.FORM_VALIDATION, ErrorMessages.FORM_VALIDATION, errors));
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleClientNotFoundException(ClientNotFoundException ex) {
        return ResponseEntity.notFound()
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorDTO(ErrorCodes.VEHICLE_MODEL_NOT_FOUND, ErrorMessages.VEHICLE_MODEL_NOT_FOUND));
    }
}

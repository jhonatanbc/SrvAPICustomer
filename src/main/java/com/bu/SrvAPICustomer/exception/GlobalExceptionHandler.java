package com.bu.SrvAPICustomer.exception;

import com.bu.SrvAPICustomer.model.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private final ObjectMapper objectMapper;
    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        try {
            Customer customer = (Customer) ex.getBindingResult().getTarget();
            log.info("idTx:"+customer.getIdTx()+" - Request Body: {}", objectMapper.writeValueAsString(customer));
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });


            Map<String, Object> response = new HashMap<>();
            response.put("idTx", customer != null ? customer.getIdTx() : "idTx no disponible");
            response.put("error", errors);
            log.info("idTx:"+customer.getIdTx()+" - Response Body: {}", objectMapper.writeValueAsString(response));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            log.error(" Error técnico en servicio /consultarCliente: {}" + e);
            return (ResponseEntity<Map<String, Object>>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.jvitorsaugusto.vetclinic_api.handler;

import com.jvitorsaugusto.vetclinic_api.dtos.ExceptionsDtos.ExceptionDto;
import com.jvitorsaugusto.vetclinic_api.exceptions.NotFoundResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundResource.class)
    private ResponseEntity<ExceptionDto> handlerNotFoundResource(NotFoundResource ex){

        ExceptionDto exceptionDto = ExceptionDto.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Recurso não encontrado")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
}

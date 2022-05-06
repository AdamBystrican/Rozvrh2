package com.example.Rozvrh.Timeblock.controller;

import com.example.Rozvrh.Timeblock.service.TimeblockServiceException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    //@ExceptionHandler(TimeblockServiceException.class)
    public ResponseEntity<ErrorDto> handleException(TimeblockServiceException timeblockServiceException){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorCode("Kolizia");
        errorDto.setMessage("taka a taka kolizia");

        return ResponseEntity.status(410)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDto);
    }
}

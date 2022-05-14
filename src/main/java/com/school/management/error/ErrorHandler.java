package com.school.management.error;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ErrorHandler {

    // =================================================
    // Class variables
    // =================================================

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    // =================================================
    // Instance variables
    // =================================================


    // =================================================
    // Public Methods
    // =================================================

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleException(Exception exception) {
        LOGGER.error("METHOD=handleException  ERR_MSG=Request failed due to error={}",
                (exception.getMessage()));
        exception.printStackTrace();
        ResponseEntity<ErrorResponseDTO> responseEntity = null;
        ErrorResponseDTO errorDTO = new ErrorResponseDTO();
        errorDTO.setErrorCode("101-01");
        errorDTO.setErrorResponse(exception.getMessage());
        errorDTO.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
}

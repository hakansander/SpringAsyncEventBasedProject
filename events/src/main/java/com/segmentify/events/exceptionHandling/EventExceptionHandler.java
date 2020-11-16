package com.segmentify.events.exceptionHandling;

import com.segmentify.events.model.enums.StatusCodeConstants;
import com.segmentify.events.model.response.EventResponse;
import com.segmentify.events.util.CommonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class EventExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EventResponse> handleWrongPathException(NoHandlerFoundException exc) {
        return new ResponseEntity<>(CommonUtil.createEventResponse(StatusCodeConstants.WRONG_PATH), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<EventResponse> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exc) {
        return new ResponseEntity<>(CommonUtil.createEventResponse(StatusCodeConstants.METHOD_NOT_ALLOWED), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler
    public ResponseEntity<EventResponse> handleInvalidJsonException(HttpMessageNotReadableException exc) {
        return new ResponseEntity<>(CommonUtil.createEventResponse(StatusCodeConstants.BAD_INPUT), HttpStatus.BAD_REQUEST);
    }
}

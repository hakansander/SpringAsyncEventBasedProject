package com.segmentify.events.exceptionHandling;

import com.segmentify.events.model.response.EventResponse;
import com.segmentify.events.util.CommonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EventExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EventResponse> handleException(IllegalStateException exc) {
        return new ResponseEntity<>(CommonUtil.createEventResponse(HttpStatus.BAD_REQUEST), HttpStatus.OK);
    }
}

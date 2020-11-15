package com.segmentify.events.util;

import com.segmentify.events.model.response.EventResponse;
import org.springframework.http.HttpStatus;

public class CommonUtil {
    public static EventResponse createEventResponse(HttpStatus httpStatus) {
        return new EventResponse(TimeUtil.getCurrentUnixTimestamp(), Integer.toString(httpStatus.value()));
    }
}

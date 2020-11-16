package com.segmentify.events.util;

import com.segmentify.events.model.enums.StatusCodeConstants;
import com.segmentify.events.model.response.EventResponse;

public class CommonUtil {
    public static EventResponse createEventResponse(StatusCodeConstants statusCode) {
        return new EventResponse(TimeUtil.getCurrentUnixTimestamp(), statusCode.name());
    }
}

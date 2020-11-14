package com.segmentify.events.service;

import com.segmentify.events.model.request.EventRequest;
import com.segmentify.events.model.response.EventResponse;

public interface EventService {
    EventResponse postEvent(String apiKey, EventRequest[] eventRequest);
}

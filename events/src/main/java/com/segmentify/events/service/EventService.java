package com.segmentify.events.service;

import com.segmentify.events.model.request.EventRequest;
import com.segmentify.events.model.response.EventResponse;

import java.util.List;

public interface EventService {
    EventResponse postEvent(String apiKey, List<EventRequest> eventRequest);
}

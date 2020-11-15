package com.segmentify.events.service;

import com.segmentify.events.model.request.EventRequest;
import com.segmentify.events.model.response.EventResponse;

import java.util.List;

public interface EventService {
    EventResponse checkPostEventRequest(String apiKey, List<EventRequest> eventRequest);
    void storeEvents(String apiKey, List<EventRequest> eventRequest);
}

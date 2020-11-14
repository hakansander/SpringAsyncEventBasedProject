package com.segmentify.events.service.Impl;

import com.segmentify.events.model.request.EventRequest;
import com.segmentify.events.model.response.EventResponse;
import com.segmentify.events.repository.EventStore;
import com.segmentify.events.service.EventService;
import com.segmentify.events.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventStore eventStore;

    public EventResponse postEvent(String apiKey, EventRequest[] eventRequest) {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setStatusCode(Integer.toString(HttpStatus.OK.value()));
        eventResponse.setTimestamp(TimeUtil.getCurrentUnixTimestamp());

        return eventResponse;
    }

}

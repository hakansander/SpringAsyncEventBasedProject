package com.segmentify.events.controller;

import com.segmentify.events.model.request.EventRequest;
import com.segmentify.events.model.response.EventResponse;
import com.segmentify.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/add/events/")
public class EventController {

    @Autowired
    EventService eventService;

    @RequestMapping(value="v1.json", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EventResponse checkPostEventRequests(@RequestParam(value = "apiKey") String apiKey,
                                    @RequestBody List<EventRequest> eventRequest) {

        EventResponse eventResponse = eventService.checkPostEventRequest(apiKey, eventRequest);

        //asynchronous storage
        if(Integer.toString(HttpStatus.OK.value()).equals(eventResponse.getStatusCode())) {
            eventService.storeEvents(apiKey, eventRequest);
        }

        return eventResponse;

    }
}

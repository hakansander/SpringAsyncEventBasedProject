package com.segmentify.events.service.Impl;

import com.segmentify.events.model.event.BaseEvent;
import com.segmentify.events.model.event.PageViewPostedEvent;
import com.segmentify.events.model.event.ProductViewPostedEvent;
import com.segmentify.events.model.request.EventRequest;
import com.segmentify.events.model.response.EventResponse;
import com.segmentify.events.repository.EventStore;
import com.segmentify.events.service.EventService;
import com.segmentify.events.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    @Autowired
    private EventStore eventStore;

    private static final String PRODUCT_VIEW = "PRODUCT_VIEW";
    private static final String PAGE_VIEW = "PAGE_VIEW";

    public EventResponse postEvent(String apiKey, List<EventRequest> eventRequest) {
        log.info("[postEvent] Method is called :: apiKey={}, eventRequest={}", apiKey, eventRequest);

        if(eventRequest.isEmpty()) {
            log.error("[postEvent] The event list is empty :: apiKey={}", apiKey);
            return createEventResponse(HttpStatus.NO_CONTENT);
        }

        BaseEvent postedEvent = null;
        for (EventRequest event : eventRequest) {
            if(event.getName().equals(PAGE_VIEW)) {
                postedEvent = new PageViewPostedEvent();
                BeanUtils.copyProperties(event, postedEvent);
            }

            else if(event.getName().equals(PRODUCT_VIEW)) {
                postedEvent = new ProductViewPostedEvent();
                BeanUtils.copyProperties(event, postedEvent);
            }

            eventStore.addEvent(postedEvent.getUserId(), postedEvent);
        }

        log.info("[postEvent] Events are successfully stored :: apiKey={}, eventRequest={}", apiKey, eventRequest);
        return createEventResponse(HttpStatus.OK);
    }

    private EventResponse createEventResponse(HttpStatus httpStatus) {
        return new EventResponse(TimeUtil.getCurrentUnixTimestamp(), Integer.toString(httpStatus.value()));
    }

}

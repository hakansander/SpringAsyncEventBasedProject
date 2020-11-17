package com.segmentify.events.service.Impl;

import com.segmentify.events.model.enums.StatusCodeConstants;
import com.segmentify.events.model.dao.BaseEvent;
import com.segmentify.events.model.dao.PageViewPostedEvent;
import com.segmentify.events.model.dao.ProductViewPostedEvent;
import com.segmentify.events.model.request.EventRequest;
import com.segmentify.events.model.response.EventResponse;
import com.segmentify.events.repository.EventStore;
import com.segmentify.events.service.EventService;
import com.segmentify.events.util.FormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.segmentify.events.util.CommonUtil.createEventResponse;

@Service
@Slf4j
public class EventServiceImpl implements EventService {
    @Autowired
    private EventStore eventStore;

    private static final String PRODUCT_VIEW = "PRODUCT_VIEW";
    private static final String PAGE_VIEW = "PAGE_VIEW";

    public EventResponse checkPostEventRequest(String apiKey, List<EventRequest> eventRequest) {
        log.info("[checkPostEventRequest] Method is called :: apiKey={}, eventRequest={}", apiKey, eventRequest);

        if(eventRequest.isEmpty()) {
            log.error("[checkPostEventRequest] The event list is empty :: apiKey={}", apiKey);
            return createEventResponse(StatusCodeConstants.NO_EVENT);
        }

        for (EventRequest event : eventRequest) {
            if(event.getUserId() == null) {
                log.error("[checkPostEventRequest] The user id is missing for the event :: apiKey={}, event={}", apiKey, event);
                return createEventResponse(StatusCodeConstants.NO_USERID);
            } else if(event.getSessionId() == null) {
                log.error("[checkPostEventRequest] The session id is missing for the event :: apiKey={}, event={}", apiKey, event);
                return createEventResponse(StatusCodeConstants.NO_SESSIONID);
            } else if(event.getName() == null) {
                log.error("[checkPostEventRequest] The event name is null :: apiKey={}, eventName={}", apiKey, event.getName());
                return createEventResponse(StatusCodeConstants.BAD_INPUT);
            } else if(PAGE_VIEW.equals(event.getName()) && !FormatUtil.isPageViewCategoryValid(event.getCategory())) {
                log.error("[checkPostEventRequest] The page view category name is wrong :: apiKey={}, category={}", apiKey, event.getCategory());
                return createEventResponse(StatusCodeConstants.BAD_INPUT);
            }
        }

        log.info("[checkPostEventRequest] Events are successfully stored :: apiKey={}, eventRequest={}", apiKey, eventRequest);
        return createEventResponse(StatusCodeConstants.SUCCESS);
    }

    @Async("asyncExecutor")
    public void storeEvents(String apiKey, List<EventRequest> eventRequest) {
        log.info("[storeEvents] Method is called :: apiKey={}, eventRequest={}", apiKey, eventRequest);

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

            if(postedEvent != null) {
                eventStore.addEvent(postedEvent.getUserId(), postedEvent);
            }
        }
    }
}

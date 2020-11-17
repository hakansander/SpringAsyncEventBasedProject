package com.segmentify.events.service;

import com.segmentify.events.model.enums.PageViewConstants;
import com.segmentify.events.model.enums.StatusCodeConstants;
import com.segmentify.events.model.request.EventRequest;
import com.segmentify.events.model.response.EventResponse;
import com.segmentify.events.repository.EventStore;
import com.segmentify.events.service.Impl.EventServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class EventServiceTest {
    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventStore eventStore;

    private String mockApiKey = "xxxx-yyyy";

    @Test
    public void successfulEvent() {
        List<EventRequest> mockEventRequestList = new ArrayList<>();

        EventRequest mockEventRequest =  EventRequest.builder()
                .userId("xxxx")
                .sessionId("yyyy")
                .name("PAGE_VIEW")
                .category(PageViewConstants.CATEGORY_PAGE.getPageViewConstant())
                .build();

        mockEventRequestList.add(mockEventRequest);

        EventResponse eventResponse = eventService.checkPostEventRequest(mockApiKey, mockEventRequestList);

        Assert.assertEquals(StatusCodeConstants.SUCCESS.toString(), eventResponse.getStatusCode());
        Assert.assertNotNull(eventResponse.getTimestamp());
    }

    @Test
    public void nullEventName() {
        List<EventRequest> mockEventRequestList = new ArrayList<>();

        EventRequest mockEventRequest =  EventRequest.builder()
                .userId("xxxx")
                .sessionId("yyyy")
                .build();

        mockEventRequestList.add(mockEventRequest);

        EventResponse eventResponse = eventService.checkPostEventRequest(mockApiKey, mockEventRequestList);

        Assert.assertEquals(StatusCodeConstants.BAD_INPUT.toString(), eventResponse.getStatusCode());
        Assert.assertNotNull(eventResponse.getTimestamp());
    }

    @Test
    public void invalidPageViewCategory() {
        List<EventRequest> mockEventRequestList = new ArrayList<>();

        EventRequest mockEventRequest =  EventRequest.builder()
                .userId("xxxx")
                .sessionId("yyyy")
                .name("PAGE_VIEW")
                .category("Hakan Sander")
                .build();

        mockEventRequestList.add(mockEventRequest);

        EventResponse eventResponse = eventService.checkPostEventRequest(mockApiKey, mockEventRequestList);

        Assert.assertEquals(StatusCodeConstants.BAD_INPUT.toString(), eventResponse.getStatusCode());
        Assert.assertNotNull(eventResponse.getTimestamp());
    }

    @Test
    public void noUserId() {
        List<EventRequest> mockEventRequestList = new ArrayList<>();

        EventRequest mockEventRequest =  EventRequest.builder()
                .sessionId("yyyy")
                .build();

        mockEventRequestList.add(mockEventRequest);

        EventResponse eventResponse = eventService.checkPostEventRequest(mockApiKey, mockEventRequestList);

        Assert.assertEquals(StatusCodeConstants.NO_USERID.toString(), eventResponse.getStatusCode());
        Assert.assertNotNull(eventResponse.getTimestamp());
    }

    @Test
    public void noSessionId() {
        List<EventRequest> mockEventRequestList = new ArrayList<>();

        EventRequest mockEventRequest =  EventRequest.builder()
                .userId("xxxx")
                .build();

        mockEventRequestList.add(mockEventRequest);

        EventResponse eventResponse = eventService.checkPostEventRequest(mockApiKey, mockEventRequestList);

        Assert.assertEquals(StatusCodeConstants.NO_SESSIONID.toString(), eventResponse.getStatusCode());
        Assert.assertNotNull(eventResponse.getTimestamp());
    }

    @Test
    public void nullEventRequest() {
        List<EventRequest> mockEventRequestList = new ArrayList<>();

        EventResponse eventResponse = eventService.checkPostEventRequest(mockApiKey, mockEventRequestList);

        Assert.assertEquals(StatusCodeConstants.NO_EVENT.toString(), eventResponse.getStatusCode());
        Assert.assertNotNull(eventResponse.getTimestamp());
    }
}

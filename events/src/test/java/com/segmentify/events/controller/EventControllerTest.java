package com.segmentify.events.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.segmentify.events.model.enums.PageViewConstants;
import com.segmentify.events.model.enums.StatusCodeConstants;
import com.segmentify.events.model.request.EventRequest;
import com.segmentify.events.model.response.EventResponse;
import com.segmentify.events.service.EventService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    private static ObjectMapper mapper = new ObjectMapper();

    private String mockTimestamp = "1605650627";

    @Before
    void setUp() {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    void successfulPostRequest_shouldReturnSuccess() throws Exception {
        EventResponse mockEventResponse = new EventResponse(mockTimestamp, StatusCodeConstants.SUCCESS.toString());

        Mockito.when(eventService.checkPostEventRequest(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(mockEventResponse);

        EventRequest mockEventRequest =  EventRequest.builder()
                .userId("xxxx")
                .sessionId("yyyy")
                .name("PAGE_VIEW")
                .category(PageViewConstants.CATEGORY_PAGE.getPageViewConstant())
                .build();

        String json = null;
        List<EventRequest> listArr = new ArrayList<>();

        try {
            listArr.add(mockEventRequest);
            json = mapper.writeValueAsString(listArr);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        mockMvc.perform(post("/add/events/v1.json").param("apiKey", "xxxxx").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode", Matchers.equalTo(StatusCodeConstants.SUCCESS.toString())))
                .andExpect(jsonPath("$.timestamp", Matchers.equalTo(mockTimestamp)));
    }

    @Test
    void invalidPath_shouldReturnWrongPath() throws Exception {
        mockMvc.perform(post("/add/events/v4.json").param("apiKey", "xxxxx").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content("[]").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode", Matchers.equalTo(StatusCodeConstants.WRONG_PATH.toString())))
                .andExpect(jsonPath("$.timestamp", Matchers.notNullValue()));
    }

    @Test
    void invalidMethodType_shouldReturnMethodNotAllowed() throws Exception {
        mockMvc.perform(get("/add/events/v1.json").param("apiKey", "xxxxx").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content("[]").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode", Matchers.equalTo(StatusCodeConstants.METHOD_NOT_ALLOWED.toString())))
                .andExpect(jsonPath("$.timestamp", Matchers.notNullValue()));
    }


    @Test
    void invalidJson_shouldReturnBadInput() throws Exception {
        mockMvc.perform(post("/add/events/v1.json").param("apiKey", "xxxxx").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content("]").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode", Matchers.equalTo(StatusCodeConstants.BAD_INPUT.toString())))
                .andExpect(jsonPath("$.timestamp", Matchers.notNullValue()));
    }

    @Test
    void nullEventName_shouldReturnBadInput() throws Exception {
        EventResponse mockEventResponse = new EventResponse(mockTimestamp, StatusCodeConstants.BAD_INPUT.toString());

        Mockito.when(eventService.checkPostEventRequest(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(mockEventResponse);

        EventRequest mockEventRequest =  EventRequest.builder()
                .userId("xxxx")
                .sessionId("yyyy")
                .category(PageViewConstants.CATEGORY_PAGE.getPageViewConstant())
                .build();

        String json = null;
        List<EventRequest> listArr = new ArrayList<>();

        try {
            listArr.add(mockEventRequest);
            json = mapper.writeValueAsString(listArr);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        mockMvc.perform(post("/add/events/v1.json").param("apiKey", "xxxxx").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode", Matchers.equalTo(StatusCodeConstants.BAD_INPUT.toString())))
                .andExpect(jsonPath("$.timestamp", Matchers.equalTo(mockTimestamp)));
    }

}

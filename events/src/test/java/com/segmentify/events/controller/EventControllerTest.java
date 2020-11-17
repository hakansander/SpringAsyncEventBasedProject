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
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

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
    public void testPostExample() throws Exception {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setStatusCode(StatusCodeConstants.SUCCESS.toString());
        eventResponse.setTimestamp(mockTimestamp);
        Mockito.when(eventService.checkPostEventRequest(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(eventResponse);

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

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("apiKey", "1");


        mockMvc.perform(post("/add/events/v1.json").param("apiKey", "xxxxx").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode", Matchers.equalTo(StatusCodeConstants.SUCCESS.toString())))
                .andExpect(jsonPath("$.timestamp", Matchers.equalTo(mockTimestamp)));
    }
}

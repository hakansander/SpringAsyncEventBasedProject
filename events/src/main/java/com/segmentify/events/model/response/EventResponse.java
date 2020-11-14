package com.segmentify.events.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EventResponse {
    String timestamp;
    String statusCode;
}

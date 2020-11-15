package com.segmentify.events.model.response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {
    String timestamp;
    String statusCode;
}

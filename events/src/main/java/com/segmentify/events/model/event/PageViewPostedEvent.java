package com.segmentify.events.model.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PageViewPostedEvent extends BaseEvent {
    private String category;
    private String subCategory;
}

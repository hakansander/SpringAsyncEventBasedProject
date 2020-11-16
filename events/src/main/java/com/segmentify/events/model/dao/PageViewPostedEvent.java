package com.segmentify.events.model.dao;

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

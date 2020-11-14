package com.segmentify.events.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class EventRequest {
    String name;
    String userId;
    String sessionId;
    String pageUrl;
    String referrer;
    String device;
    String productId;
    String title;
    String inStock;
    String url;
    String image;
    String category;
    String brand;
    String price;
    String oldPrice;
    String gender;
    String colors;
    String sizes;
    String labels;
    List<String> params;
}


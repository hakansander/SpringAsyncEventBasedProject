package com.segmentify.events.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class EventRequest {
    private String category;
    private String subCategory;
    private String productId;
    private String title;
    private String inStock;
    private String url;
    private String mUrl;
    private String image;
    private String imageXS;
    private String imageS;
    private String imageM;
    private String imageL;
    private String imageXL;
    private String brand;
    private String price;
    private String oldPrice;
    private String currency;
    private String gender;
    private String colors;
    private String sizes;
    private String labels;
    private String name;
    private String userId;
    private String sessionId;
    private String device;
    private String userAgent;
    private String lang;
    private String browser;
    private String os;
    private String osversion;
    private String pageUrl;
    private String referrer;
    private Map<String,String> params;
    private String testMode;
    private String nextPage;
}


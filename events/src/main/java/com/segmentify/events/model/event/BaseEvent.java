package com.segmentify.events.model.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEvent {
    private String name;
    private String userId;
    private String sessionId;
    private String device;
    private String userAgent;
    private String lang;
    private String currency;
    private String browser;
    private String os;
    private String osversion;
    private String pageUrl;
    private String referrer;
    private String params;
    private String testMode;
    private String nextPage;
}
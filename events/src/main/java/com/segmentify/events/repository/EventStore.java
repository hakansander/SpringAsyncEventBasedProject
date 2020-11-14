package com.segmentify.events.repository;

import com.segmentify.events.model.event.BaseEvent;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EventStore {
    private Map<String, List<BaseEvent>> store = new HashMap<>();
}
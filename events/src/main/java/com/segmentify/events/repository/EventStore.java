package com.segmentify.events.repository;

import com.segmentify.events.model.event.BaseEvent;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EventStore {
    private Map<String, List<BaseEvent>> store = new HashMap<>();

    public void addEvent(String id, BaseEvent event) {
        List<BaseEvent> events = store.get(id);
        if (events == null) {
            events = new ArrayList<BaseEvent>();
            events.add(event);
            store.put(id, events);
        } else {
            events.add(event);
        }
    }
}
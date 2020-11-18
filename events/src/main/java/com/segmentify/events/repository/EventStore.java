package com.segmentify.events.repository;

import com.segmentify.events.model.dao.BaseEvent;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EventStore {
    private static EventStore singleInstance = null;
    private Map<String, List<BaseEvent>> store = null;

    private EventStore() {
        store = new HashMap<>();
    }

    public synchronized static EventStore getInstance()
    {
        if (singleInstance == null)
            singleInstance = new EventStore();

        return singleInstance;
    }

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
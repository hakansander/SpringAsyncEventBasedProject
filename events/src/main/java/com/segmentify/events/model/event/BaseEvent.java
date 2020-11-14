package com.segmentify.events.model.event;

import java.util.Date;
import java.util.UUID;

public abstract class BaseEvent {
    public final UUID id = UUID.randomUUID();
    public final Date created = new Date();
}
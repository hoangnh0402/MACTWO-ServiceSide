package com.mactwo.event.base;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class EventBase {
    private UUID eventId = UUID.randomUUID();
    private LocalDateTime eventTimestamp = LocalDateTime.now();
}

package com.test.EventLogger;

import com.test.event.Event;
import org.springframework.stereotype.Component;

public interface EventLogger {
    void logEvent(Event event);
}

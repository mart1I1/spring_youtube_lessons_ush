package com.test.EventLogger;

import com.test.event.Event;
import org.springframework.stereotype.Component;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}

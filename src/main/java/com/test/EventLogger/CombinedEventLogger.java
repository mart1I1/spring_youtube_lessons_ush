package com.test.EventLogger;

import com.test.event.Event;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

public class CombinedEventLogger implements EventLogger {

    private List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        loggers.forEach(it -> it.logEvent(event));
    }
}

package com.test.app;

import com.test.EventLogger.EventLogger;
import com.test.client.Client;
import com.test.event.Event;
import com.test.event.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

public class App {

    private Client client;

    private EventLogger eventLogger;

    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(LoggerConfig.class, AppConfig.class);
//        context.scan("com.test");
//        context.refresh();

        App app = context.getBean("app", App.class);
        Event event = context.getBean(Event.class);

        app.logEvent(EventType.ERROR, event);
        app.logEvent(EventType.INFO, event);

        System.out.println(app.client.toString());

        context.close();
    }

    private void logEvent(EventType type, Event event) {
//        String message = msg.replaceAll(com.test.client.getId(), com.test.client.getFullName());
        EventLogger logger = loggers.get(type);
        if (logger == null)
            logger = eventLogger;
        logger.logEvent(event);
    }
}

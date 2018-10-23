package com.test.config;

import com.test.EventLogger.*;
import com.test.event.Event;
import com.test.event.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

@Configuration
@PropertySource("classpath:client.properties")
public class LoggersConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Resource(name = "consoleEventLogger")
    private EventLogger consoleEventLogger;

    @Resource(name = "fileEventLogger")
    private EventLogger fileEventLogger;

    @Resource(name = "cacheFileEventLogger")
    private EventLogger cacheFileEventLogger;

    @Resource(name = "combinedEventLogger")
    private EventLogger combinedEventLogger;


    @Bean(name = "loggerList")
    List<EventLogger> getLoggerList() {
        List<EventLogger> list = new ArrayList<>();
        list.add(fileEventLogger);
        list.add(consoleEventLogger);
        return list;
    }

    @Bean(name = "loggerMap")
    Map<EventType, EventLogger> getLoggerMap() {
        Map<EventType, EventLogger> map = new HashMap<>();
        map.put(EventType.ERROR, consoleEventLogger);
        map.put(EventType.INFO, combinedEventLogger);
        return map;
    }

    @Bean(name = "defaultEventLogger")
    EventLogger getDefaultEventLogger() {
        return cacheFileEventLogger;
    }

}

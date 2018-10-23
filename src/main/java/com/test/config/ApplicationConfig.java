package com.test.config;

import com.test.client.Client;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.text.DateFormat;
import java.util.Date;

@Configuration
public class ApplicationConfig {

    @Bean
    Date getDate() {
        return new Date();
    }

    @Bean
    DateFormat getDateFormat() {
        return DateFormat.getDateInstance();
    }

}

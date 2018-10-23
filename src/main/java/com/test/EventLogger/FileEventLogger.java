package com.test.EventLogger;

import com.test.event.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class FileEventLogger implements EventLogger {

    @Value("${filePath}")
    private File file;

    public FileEventLogger() {
    }

    public FileEventLogger(String filename) throws IOException {
        this.file = new File(filename);
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\n", Charset.defaultCharset(), true);
        } catch (IOException e) {
            System.out.println("write to file exception");
        }
    }

    @PostConstruct
    public void init() throws IOException {
        if (!this.file.canWrite())
            file.createNewFile();
    }
}

package net.alexander.backdata.log;

import net.alexander.backdata.service.Service;
import org.apache.log4j.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerManager implements Service {

    private Logger logger = Logger.getRootLogger();

    public LoggerManager() {
        File folder = new File("BackData/logs/");
        if (!folder.exists()) {
            folder.mkdir();
        }

        String logFile = "BackData/logs/log-" + new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date()) + ".log/";

        try {
            File file = new File(logFile);
            file.createNewFile();
            PatternLayout layout = new PatternLayout("%d{dd-MM-yyyy HH:mm:ss} [%p] - %m%n");
            ConsoleAppender consoleAppender = new ConsoleAppender(layout);
            logger.addAppender(consoleAppender);
            FileAppender fileAppender = new FileAppender(layout, logFile, false);
            logger.addAppender(fileAppender);
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String message) {
        log(Priority.INFO, message);
    }

    public void log(Priority priority, String message) {
        logger.log(priority, message);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}

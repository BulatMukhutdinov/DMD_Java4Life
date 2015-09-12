package com.innopolis.courses.dmd.premasters.java4life;

import java.io.IOException;
import java.util.logging.*;

public class LoggerWrapper {
    public static final Logger wrapper = Logger.getLogger(LoggerWrapper.class.getName());

    private static LoggerWrapper instance = null;

    public static LoggerWrapper getInstance() {
        if (instance == null) {
            createLogger();
            instance = new LoggerWrapper();
        }
        return instance;
    }

    private static void createLogger() {
        try {
            FileHandler fileHandler = new FileHandler("logs/Log Info", true);
            wrapper.addHandler(fileHandler);
            HtmlFormatter htmlformatter = new HtmlFormatter();
            FileHandler htmlFile = new FileHandler("logs/Log Info.htm", true);
            htmlFile.setFormatter(htmlformatter);
            wrapper.addHandler(htmlFile);
            wrapper.addHandler(new Handler() {
                @Override
                public void publish(LogRecord record) {
                    if (getFormatter() == null) {
                        setFormatter(new SimpleFormatter());
                    }
                    try {
                        String message = getFormatter().format(record);
                        if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
                            System.err.write(message.getBytes());
                        } else {
                            System.out.write(message.getBytes());
                        }
                    } catch (Exception exception) {
                        reportError(null, exception, ErrorManager.FORMAT_FAILURE);
                    }
                }
                @Override
                public void flush() {
                }

                @Override
                public void close() throws SecurityException {
                }
            });
            wrapper.setUseParentHandlers(false);
        } catch (SecurityException e) {
            wrapper.log(Level.SEVERE,
                    "Failed to create a log file because of security policies.",
                    e);
        } catch (IOException e) {
            wrapper.log(Level.SEVERE,
                    "Failed to create a log file because of an error IO.",
                    e);
        }
    }
}

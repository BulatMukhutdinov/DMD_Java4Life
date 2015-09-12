package com.innopolis.courses.dmd.premasters.java4life;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            FileHandler fh = new FileHandler("Log Info");
            wrapper.addHandler(fh);
            HtmlFormatter htmlformatter = new HtmlFormatter();
            FileHandler htmlFile = new FileHandler("Log Info.htm");
            htmlFile.setFormatter(htmlformatter);
            wrapper.addHandler(htmlFile);
        } catch (SecurityException e) {
            wrapper.log(Level.SEVERE,
                    "Не удалось создать файл лога из-за политики безопасности.",
                    e);
        } catch (IOException e) {
            wrapper.log(Level.SEVERE,
                    "Не удалось создать файл лога из-за ошибки ввода-вывода.",
                    e);
        }
    }
}

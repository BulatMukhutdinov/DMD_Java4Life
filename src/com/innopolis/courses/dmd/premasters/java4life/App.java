package com.innopolis.courses.dmd.premasters.java4life;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    private final static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        createLogger();
        logger.log(Level.INFO, "Record of the log with INFO level");
        logger.log(Level.WARNING, "Record of the log with WARNING level");
        try {
            throw new Exception("Test message about error");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Record of the log with SEVERE level" + e);
        }

    }

    public static void createLogger() {
        try {
            FileHandler fh = new FileHandler("Log Info");
            logger.addHandler(fh);
            HtmlFormatter htmlformatter = new HtmlFormatter();
            FileHandler htmlFile = new FileHandler("Log Info.htm");
            htmlFile.setFormatter(htmlformatter);
            logger.addHandler(htmlFile);
        } catch (SecurityException e) {
            logger.log(Level.SEVERE,
                    "Не удалось создать файл лога из-за политики безопасности.",
                    e);
        } catch (IOException e) {
            logger.log(Level.SEVERE,
                    "Не удалось создать файл лога из-за ошибки ввода-вывода.",
                    e);
        }
    }
}

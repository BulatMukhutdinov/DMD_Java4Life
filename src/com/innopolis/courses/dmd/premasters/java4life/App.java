package com.innopolis.courses.dmd.premasters.java4life;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    private final static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        try {
            FileHandler fh = new FileHandler("Log Info");
            logger.addHandler(fh);
            HtmlFormatter htmlformatter = new HtmlFormatter();
            FileHandler htmlfile = new FileHandler("Log Info.htm");
            htmlfile.setFormatter(htmlformatter);
            logger.addHandler(htmlfile);
        } catch (SecurityException e) {
            logger.log(Level.SEVERE,
                    "Не удалось создать файл лога из-за политики безопасности.",
                    e);
        } catch (IOException e) {
            logger.log(Level.SEVERE,
                    "Не удалось создать файл лога из-за ошибки ввода-вывода.",
                    e);
        }
        logger.log(Level.INFO, "Запись лога с уровнем INFO (информационная)");
        logger.log(Level.WARNING, "Запись лога с уровнем WARNING (Предупреждение)");
        logger.log(Level.SEVERE, "Запись лога с уровнем SEVERE (серъёзная ошибка)",
                new Exception("Проверочное сообщение об ошибке"));
    }
}

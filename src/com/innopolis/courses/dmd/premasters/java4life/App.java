package com.innopolis.courses.dmd.premasters.java4life;

public class App {
    private final static LoggerWrapper logger = LoggerWrapper.getInstance();

    public static void main(String[] args) {
        DBManager.createDB();
    }
}

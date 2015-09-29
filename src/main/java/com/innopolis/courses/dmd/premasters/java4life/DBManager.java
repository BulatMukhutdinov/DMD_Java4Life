package com.innopolis.courses.dmd.premasters.java4life;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class DBManager {
    public static final String URL = "jdbc:postgresql://localhost:5432/";
    public static final String USER = "postgres";
    public static final String PASS = "postgres";
    public static final String DB_CREATION = "src/main/resources/sql/dbCreation";
    public static final String TABLES_CREATION = "src/main/resources/sql/tablesCreation";
    public static final String CONSTRAINTS = "src/main/resources/sql/constraints";
    public static final String DB_NAME = "DBLP";
    public static final String COPY_DELIMITER = ";";
    private final static LoggerWrapper logger = LoggerWrapper.getInstance();
    public static Connection conn = null;
    public static Statement stmt = null;

    public static void copyCSV() {
        logger.wrapper.log(Level.INFO, "Starting copy values from CSV files to DB...");
        File folder = new File(CSVCreator.CSV_PATH);
        File[] listOfFiles = folder.listFiles();
        try {
            for (File file : listOfFiles) {
                stmt.executeUpdate("COPY dblp.\"" + file.getName() + "\" from '" + file.getAbsolutePath() + "' (DELIMITER '" + COPY_DELIMITER + "')");
                logger.wrapper.log(Level.INFO, "Values for " + file.getName().toUpperCase() + " table successfully copied");
            }
        } catch (SQLException e) {
            logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + e);
        }
        logger.wrapper.log(Level.INFO, "All values successfully copied!");
    }

    public static void createDB() {
        try {
            logger.wrapper.log(Level.INFO, "Check if driver set...");
            Class.forName("org.postgresql.Driver");
            logger.wrapper.log(Level.INFO, "Driver set correctly");
            //DB creation
            createConnection(URL, USER, PASS);
            logger.wrapper.log(Level.INFO, "Try to create database...");
            executeSQLScript(DB_CREATION);
            conn.close();
            stmt.close();
            //tables creation
            createConnection(URL + DB_NAME, USER, PASS);
            logger.wrapper.log(Level.INFO, "Try to create tables...");
            executeSQLScript(TABLES_CREATION);
        } catch (ClassNotFoundException e) {
            logger.wrapper.log(Level.SEVERE, "Some troubles with JDBC: ", e);
        } catch (SQLException sqlException) {
            logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + sqlException);
        }
    }

    public static void createConstraints() {
        //createConnection(URL + DB_NAME, USER, PASS);
        logger.wrapper.log(Level.INFO, "Try to set constraints...");
        executeSQLScript(CONSTRAINTS);
    }

    private static void executeSQLScript(String fileLocation) {
        try {
            logger.wrapper.log(Level.INFO, "Looking for script");

            Path path = Paths.get(fileLocation);
            logger.wrapper.log(Level.INFO, "Start reading from script...");
            String sql = java.nio.file.Files.lines(path).collect(Collectors.joining());
            logger.wrapper.log(Level.INFO, "Successfully read script");
            logger.wrapper.log(Level.INFO, "Start executing queries...");
            stmt.executeUpdate(sql);
            logger.wrapper.log(Level.INFO, "Queries successfully executed");
        } catch (FileNotFoundException e) {
            logger.wrapper.log(Level.SEVERE, "SQL script file was not found. Failed to initialize the database.");
        } catch (IOException e) {
            logger.wrapper.log(Level.SEVERE, "Input/Output exception:  ", e);
        } catch (SQLException sqlException) {
            logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + sqlException);
        }
    }

    private static void createConnection(String url, String user, String pass) {
        try {
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

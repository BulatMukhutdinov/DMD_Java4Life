package com.innopolis.courses.dmd.premasters.java4life;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class DBManager {
    public static final String URL = "jdbc:postgresql://localhost:5432/";
    public static final String USER = "postgres";
    public static final String PASS = "postgres";
    public static final String SQL_SCRIPT = "resources/dbInitialization.sql";
    private final static LoggerWrapper logger = LoggerWrapper.getInstance();

    /*
        public static List<LeaderBoard> getRecords() {
            try (Connection conn = DriverManager.getConnection(URL + "snake", USER, PASS);
                 Statement stmt = conn.createStatement()) {
                Class.forName("org.postgresql.Driver");
                System.out.println("Connecting to database...");
                String sql = "select name, score, _time from leaderboard";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("Query executed successfully...");
                List<LeaderBoard> leaderBoardList = new ArrayList<LeaderBoard>();
                while (rs.next()) {
                    String name = rs.getString("name");
                    int score = rs.getInt("score");
                    double time = rs.getDouble("_time");
                    leaderBoardList.add(new LeaderBoard(score, time, name));
                }
                return leaderBoardList;
            } catch (SQLException se) {
                //Handle errors for JDBC
                se.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            }
            return new ArrayList<LeaderBoard>();
        }

        public static void setRecord(LeaderBoard leaderBoard) {
            try (Connection conn = DriverManager.getConnection(URL + "snake", USER, PASS);
                 PreparedStatement stmt = conn.prepareStatement("insert into leaderboard (name,score,_time) values (?,?,?)")) {
                Class.forName("org.postgresql.Driver");
                System.out.println("Connecting to database...");
                stmt.setString(1, leaderBoard.getName());
                stmt.setInt(2, leaderBoard.getScore());
                stmt.setDouble(3, leaderBoard.getTime());
                stmt.executeUpdate();
                System.out.println("Query executed successfully...");
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            }
        }
    */
    public static void initDB() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            logger.wrapper.log(Level.INFO, "Check if driver set...");
            Class.forName("org.postgresql.Driver");
            logger.wrapper.log(Level.INFO, "Driver set correctly");
            String line;
            StringBuffer stringBuffer = new StringBuffer();
            logger.wrapper.log(Level.INFO, "Looking for sql script");
            FileReader fileReader = new FileReader(new File(SQL_SCRIPT));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            logger.wrapper.log(Level.INFO, "Start reading from script...");
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            bufferedReader.close();
            logger.wrapper.log(Level.INFO, "Successfully read from script");
            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] queries = stringBuffer.toString().split(";");
            logger.wrapper.log(Level.INFO, "Start executing queries...");
            for (int i = 0; i < queries.length; i++) {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if (!queries[i].trim().equals("")) {
                    stmt.executeUpdate(queries[i]);
                    logger.wrapper.log(Level.INFO, queries[i]);
                }
            }
            logger.wrapper.log(Level.INFO, "Database successfully initialized");
        } catch (FileNotFoundException e) {
            logger.wrapper.log(Level.SEVERE, "SQL script file NOT founded. Failed to initialized database.");
        } catch (IOException e) {
            logger.wrapper.log(Level.SEVERE, "Input/Output exception:  ", e);
        } catch (SQLException sqlException) {
            logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + sqlException);
        } catch (ClassNotFoundException e) {
            logger.wrapper.log(Level.SEVERE, "Some troubles with JDBC: ", e);
        }
    }
}

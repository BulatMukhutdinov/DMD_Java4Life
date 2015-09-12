package com.innopolis.courses.dmd.premasters.java4life;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class DBManager {
    public static final String URL = "jdbc:postgresql://localhost:5432/";
    public static final String USER = "postgres";
    public static final String PASS = "postgres";
    public static final String DB_NAME = "dmd_dblp";
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
    public static void createDB() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            Class.forName("org.postgresql.Driver");
            logger.wrapper.log(Level.INFO, "Connecting to database...");
            String sql = "CREATE DATABASE " + DB_NAME;
            stmt.executeUpdate(sql);
            logger.wrapper.log(Level.INFO, "Database created successfully");
        } catch (SQLException sqlException) {
            if (sqlException.getSQLState().equals("42P04")) {
                logger.wrapper.log(Level.WARNING, "Database already exists");
            } else {
                logger.wrapper.log(Level.SEVERE, "Unexpected SQL exception: " + sqlException);
            }
        } catch (ClassNotFoundException e) {
            logger.wrapper.log(Level.SEVERE, "Some troubles with JDBC: ", e);
        }

    }

    public static void createTable() {
        try (Connection conn = DriverManager.getConnection(URL + DB_NAME, USER, PASS);
        ) {
            Statement stmt = conn.createStatement();
            Class.forName("org.postgresql.Driver");
            System.out.println("Creating table...");
            String sql = "create table if not exists record(id serial, mdate varchar(200), key varchar(200), " +
                    "author varchar(200), title varchar(500), pages varchar(50), year int, " +
                    "volume int, journal varchar(300), number int, path varchar(200), doi varchar(200))";
            stmt.execute(sql);
            System.out.println("Table created successfully...");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

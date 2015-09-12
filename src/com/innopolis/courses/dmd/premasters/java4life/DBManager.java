package com.innopolis.courses.dmd.premasters.java4life;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    public static final String URL = "jdbc:postgresql://localhost:5432/";
    public static final String USER = "postgres";
    public static final String PASS = "postgres";
    public static final String DN_NAME = "dmd_dblp";

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
            System.out.println("Connecting to database...");
            String sql = "CREATE DATABASE " + DN_NAME;
            stmt.executeUpdate(sql);
        } catch (SQLException sqlException) {
            if (sqlException.getSQLState().equals("42P04")) {
                // Database already exists error
            } else {
                // Some other problems, e.g. Server down, no permission, etc
                sqlException.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Database created successfully...");
    }

    public static void createTable() {
        try (Connection conn = DriverManager.getConnection(URL + DN_NAME, USER, PASS);
        ) {
            Statement stmt = conn.createStatement();
            Class.forName("org.postgresql.Driver");
            System.out.println("Creating table...");
            String sql = "create table if not exists record(id serial, name varchar(200), score int ,_time double precision )";
            stmt.execute(sql);
            System.out.println("Table created successfully...");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

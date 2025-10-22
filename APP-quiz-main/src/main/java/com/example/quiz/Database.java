package com.example.quiz;

import java.sql.*;
import javax.swing.JOptionPane;


public class Database {
    private static final String SQLITE_URL = "jdbc:sqlite:quiz.db";

    private static boolean usingMySql() {
        String t = System.getenv("DB_TYPE");
        return t != null && t.equalsIgnoreCase("mysql");
    }

    public static void init() {
        try (Connection conn = getConnection()) {
            try (Statement st = conn.createStatement()) {
                if (usingMySql()) {
                    st.execute("CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255) UNIQUE, password VARCHAR(255))");
                    st.execute("CREATE TABLE IF NOT EXISTS history (id INT AUTO_INCREMENT PRIMARY KEY, user_id INT, score INT, taken_at DATETIME, FOREIGN KEY(user_id) REFERENCES users(id))");
                } else {
                    st.execute("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT UNIQUE, password TEXT)");
                    st.execute("CREATE TABLE IF NOT EXISTS history (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, score INTEGER, taken_at TEXT, FOREIGN KEY(user_id) REFERENCES users(id))");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database init failed: " + e.getMessage());
            System.exit(1);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (usingMySql()) {
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASS");
            if (url == null) throw new SQLException("DB_URL not set for MySQL");
            return DriverManager.getConnection(url, user, pass);
        } else {
            return DriverManager.getConnection(SQLITE_URL);
        }
    }
}

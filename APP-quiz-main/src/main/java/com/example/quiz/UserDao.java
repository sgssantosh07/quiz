package com.example.quiz;

import java.sql.*;

public class UserDao {
    public static boolean register(String username, String password) {
        String sql = "INSERT INTO users(username,password) VALUES(?,?)";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static Integer login(String username, String password) {
        String sql = "SELECT id FROM users WHERE username=? AND password=?";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

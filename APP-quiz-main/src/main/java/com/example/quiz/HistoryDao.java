package com.example.quiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
    public static void addScore(int userId, int score) {
        String sql = "INSERT INTO history(user_id, score, taken_at) VALUES(?,?,datetime('now'))";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, score);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getHistoryForUser(int userId) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT score, taken_at FROM history WHERE user_id=? ORDER BY taken_at DESC";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getInt("score") + " - " + rs.getString("taken_at"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

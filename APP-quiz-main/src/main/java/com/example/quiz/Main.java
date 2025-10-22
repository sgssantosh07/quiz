package com.example.quiz;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            Database.init();
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

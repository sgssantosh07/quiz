package com.example.quiz;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cards = new CardLayout();
    private JPanel container = new JPanel(cards);
    private Integer currentUserId = null;

    public MainFrame() {
        setTitle("Java Quiz App");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        container.add(new LoginPanel(this), "login");
        container.add(new RegisterPanel(this), "register");
        container.add(new QuizPanel(this), "quiz");
        container.add(new HistoryPanel(this), "history");

        add(container);
        showLogin();
    }

    public void showLogin() { cards.show(container, "login"); }
    public void showRegister() { cards.show(container, "register"); }
    public void showQuiz() { cards.show(container, "quiz"); }
    public void showHistory() { cards.show(container, "history"); }

    public void setCurrentUserId(Integer id) { this.currentUserId = id; }
    public Integer getCurrentUserId() { return currentUserId; }
}

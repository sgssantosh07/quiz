package com.example.quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {
    public LoginPanel(MainFrame frame) {
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(3,2,5,5));
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        form.add(new JLabel("Username:")); form.add(userField);
        form.add(new JLabel("Password:")); form.add(passField);

        JButton loginBtn = new JButton("Login");
        JButton regBtn = new JButton("Register");

        form.add(loginBtn); form.add(regBtn);

        add(form, BorderLayout.CENTER);

        loginBtn.addActionListener((ActionEvent e) -> {
            String user = userField.getText().trim();
            String pass = new String(passField.getPassword()).trim();
            Integer id = UserDao.login(user, pass);
            if (id != null) {
                frame.setCurrentUserId(id);
                JOptionPane.showMessageDialog(this, "Login successful");
                frame.showQuiz();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        });

        regBtn.addActionListener(e -> frame.showRegister());
    }
}

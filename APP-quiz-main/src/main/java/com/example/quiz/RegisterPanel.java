package com.example.quiz;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    public RegisterPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(4,2,5,5));
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JPasswordField pass2Field = new JPasswordField();

        form.add(new JLabel("Username:")); form.add(userField);
        form.add(new JLabel("Password:")); form.add(passField);
        form.add(new JLabel("Confirm Password:")); form.add(pass2Field);

        JButton createBtn = new JButton("Create");
        JButton backBtn = new JButton("Back");
        form.add(createBtn); form.add(backBtn);

        add(form, BorderLayout.CENTER);

        createBtn.addActionListener(e -> {
            String user = userField.getText().trim();
            String p1 = new String(passField.getPassword()).trim();
            String p2 = new String(pass2Field.getPassword()).trim();
            if (user.isEmpty() || p1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill fields");
                return;
            }
            if (!p1.equals(p2)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match");
                return;
            }
            boolean ok = UserDao.register(user, p1);
            if (ok) {
                JOptionPane.showMessageDialog(this, "User created. Please login.");
                frame.showLogin();
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed (maybe username exists)");
            }
        });

        backBtn.addActionListener(e -> frame.showLogin());
    }
}

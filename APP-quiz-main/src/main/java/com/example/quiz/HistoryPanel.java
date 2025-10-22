package com.example.quiz;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HistoryPanel extends JPanel {
    public HistoryPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("History");
        add(title, BorderLayout.NORTH);

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);
        add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton back = new JButton("Back");
        JButton refresh = new JButton("Refresh");
        bottom.add(refresh); bottom.add(back);
        add(bottom, BorderLayout.SOUTH);

        back.addActionListener(e -> frame.showQuiz());
        refresh.addActionListener(e -> {
            model.clear();
            Integer id = frame.getCurrentUserId();
            if (id==null) {
                model.addElement("Not logged in");
                return;
            }
            List<String> items = HistoryDao.getHistoryForUser(id);
            for (String it: items) model.addElement(it);
        });
    }
}

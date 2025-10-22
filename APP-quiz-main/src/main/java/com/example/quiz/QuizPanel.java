package com.example.quiz;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QuizPanel extends JPanel {
    private MainFrame frame;
    private List<Question> questions;
    private int index = 0;
    private int score = 0;

    private JLabel qLabel = new JLabel();
    private JRadioButton[] opts = new JRadioButton[4];
    private ButtonGroup group = new ButtonGroup();

    public QuizPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new BorderLayout());
        top.add(new JLabel("Java Quiz"), BorderLayout.WEST);

        JButton hist = new JButton("History");
        hist.addActionListener(e -> frame.showHistory());
        top.add(hist, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(5,1));
        center.add(qLabel);
        for (int i=0;i<4;i++) {
            opts[i] = new JRadioButton();
            group.add(opts[i]);
            center.add(opts[i]);
        }
        add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton next = new JButton("Next");
        JButton start = new JButton("Start Quiz");
        bottom.add(start);
        bottom.add(next);
        add(bottom, BorderLayout.SOUTH);

        start.addActionListener(e -> startQuiz());
        next.addActionListener(e -> nextQuestion());
    }

    private void startQuiz() {
        questions = QuestionBank.getRandomQuestions(5);
        index = 0; score = 0;
        showCurrent();
    }

    private void showCurrent() {
        Question q = questions.get(index);
        qLabel.setText((index+1)+") "+q.getQuestion());
        List<String> optsList = q.getOptions();
        for (int i=0;i<4;i++) {
            opts[i].setText(optsList.get(i));
            opts[i].setSelected(false);
        }
    }

    private void nextQuestion() {
        if (questions==null) return;
        int selected = -1;
        for (int i=0;i<4;i++) if (opts[i].isSelected()) selected = i;
        if (selected==-1) {
            JOptionPane.showMessageDialog(this, "Please select an option");
            return;
        }
        if (selected == questions.get(index).getCorrectIndex()) score++;
        index++;
        if (index >= questions.size()) {
            JOptionPane.showMessageDialog(this, "Quiz finished. Score: " + score);
            if (frame.getCurrentUserId()!=null) HistoryDao.addScore(frame.getCurrentUserId(), score);
            frame.showHistory();
        } else {
            showCurrent();
        }
    }
}

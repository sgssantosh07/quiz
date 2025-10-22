package com.example.quiz;

import java.util.*;

public class QuestionBank {
    private static final List<Question> QUESTIONS = new ArrayList<>();

    static {
        QUESTIONS.add(new Question("What is the size of int in Java?",
                Arrays.asList("8 bit","16 bit","32 bit","64 bit"),2));
        QUESTIONS.add(new Question("Which keyword is used to inherit a class in Java?",
                Arrays.asList("implements","extends","inherits","uses"),1));
        QUESTIONS.add(new Question("Which collection is ordered and allows duplicate elements?",
                Arrays.asList("Set","Map","List","Queue"),2));
        QUESTIONS.add(new Question("Which package contains the Random class?",
                Arrays.asList("java.util","java.lang","java.io","java.net"),0));
        QUESTIONS.add(new Question("Which of these is not a Java primitive type?",
                Arrays.asList("int","String","char","boolean"),1));
        
    }

    public static List<Question> getRandomQuestions(int n) {
        List<Question> copy = new ArrayList<>(QUESTIONS);
        Collections.shuffle(copy);
        return copy.subList(0, Math.min(n, copy.size()));
    }
}

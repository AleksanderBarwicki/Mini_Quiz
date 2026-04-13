package com.example.miniquiz;

public class Question {
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String correctAnswer;

    public Question(String questionText, String optionA, String optionB, String optionC, String correctAnswer) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() { return questionText; }
    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }
    public String getCorrectAnswer() { return correctAnswer; }
}
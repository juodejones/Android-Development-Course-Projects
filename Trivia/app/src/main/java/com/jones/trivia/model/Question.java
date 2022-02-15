package com.jones.trivia.model;

public class Question {

    private String quesText;
    private boolean answerTrue;

    public Question(String quesText, boolean answerTrue) {
        this.quesText = quesText;
        this.answerTrue = answerTrue;
    }

    public Question() {
    }

    public String getQuesText() {
        return quesText;
    }

    public void setQuesText(String quesText) {
        this.quesText = quesText;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }

    @Override
    public String toString() {
        return "Question{" +
                "quesText='" + quesText + '\'' +
                ", answerTrue=" + answerTrue +
                "}\n";
    }
}

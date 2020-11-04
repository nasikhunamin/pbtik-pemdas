package com.devgrafis.www.grafis.model;

public class Quiz {
    String titleQuiz, subtitleQuiz;
    public Quiz(String title, String subtitle){
        this.titleQuiz = title;
        this.subtitleQuiz = subtitle;
    }

    public String getTitleQuiz() {
        return titleQuiz;
    }

    public String getSubtitleQuiz() {
        return subtitleQuiz;
    }
}

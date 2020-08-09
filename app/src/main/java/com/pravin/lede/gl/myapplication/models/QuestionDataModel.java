package com.pravin.lede.gl.myapplication.models;

public class QuestionDataModel {

    String quetion;
    String opt1;
    String opt2;
    String opt3;
    String opt4;
    String answer;

    public QuestionDataModel(String quetion, String opt1, String opt2, String opt3, String opt4, String answer) {
        this.quetion = quetion;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.answer = answer;
    }

    public String getQuetion() {
        return quetion;
    }

    public String getOpt1() {
        return opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public String getAnswer() {
        return answer;
    }
}

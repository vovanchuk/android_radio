package com.example.lab3;

public class ModelMark {
    //pola klasu ModelMark
    private String nameSubject;
    private Integer mark;
    //konstruktor ModelMark
    public ModelMark(String nameSubject, Integer mark) {
        this.nameSubject = nameSubject;
        this.mark = mark;
        sumMarks += mark;
    }
    //getery, setery
    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        sumMarks -= this.mark;
        sumMarks += mark;
        this.mark = mark;

    }

    public static double sumMarks = 0;
}

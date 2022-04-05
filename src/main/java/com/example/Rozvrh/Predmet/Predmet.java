package com.example.Rozvrh.Predmet;

public class Predmet {

    public long id;
    private String nazovPredmetu;
    private boolean potrebujePocitace;
    public Predmet() {
    }


    public long getId() {
        return id;
    }

    public String getNazovPredmetu() {
        return nazovPredmetu;
    }

    public boolean isPotrebujePocitace() {
        return potrebujePocitace;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNazovPredmetu(String nazovPredmetu) {
        this.nazovPredmetu = nazovPredmetu;
    }

    public void setPotrebujePocitace(boolean potrebujePocitace) {
        this.potrebujePocitace = potrebujePocitace;
    }
}

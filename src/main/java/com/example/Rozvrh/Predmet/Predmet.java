package com.example.Rozvrh.Predmet;

public class Predmet {

    public Long id;
    private String nazovPredmetu;
    private boolean potrebujePocitace;
    private TypPredmetu typPredmetu;

    public TypPredmetu getTypPredmetu() {
        return typPredmetu;
    }

    public void setTypPredmetu(TypPredmetu typPredmetu) {
        this.typPredmetu = typPredmetu;
    }

    public Predmet() {
    }


    public Long getId() {
        return id;
    }

    public String getNazovPredmetu() {
        return nazovPredmetu;
    }

    public boolean isPotrebujePocitace() {
        return potrebujePocitace;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNazovPredmetu(String nazovPredmetu) {
        this.nazovPredmetu = nazovPredmetu;
    }

    public void setPotrebujePocitace(boolean potrebujePocitace) {
        this.potrebujePocitace = potrebujePocitace;
    }
}

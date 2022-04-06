package com.example.Rozvrh.Predmet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PredmetEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String nazovPredmetu;
    private boolean potrebujePocitace;
    private TypPredmetu typPredmetu;
    private int rocnik;

    public int getRocnik() {
        return rocnik;
    }

    public void setRocnik(int rocnik) {
        this.rocnik = rocnik;
    }

    public TypPredmetu getTypPredmetu() {
        return typPredmetu;
    }

    public void setTypPredmetu(TypPredmetu typPredmetu) {
        this.typPredmetu = typPredmetu;
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

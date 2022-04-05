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

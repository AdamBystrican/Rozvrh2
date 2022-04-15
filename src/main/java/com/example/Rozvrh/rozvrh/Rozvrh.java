package com.example.Rozvrh.rozvrh;

import com.example.Rozvrh.Den;
import com.example.Rozvrh.Predmet.Predmet;
import com.example.Rozvrh.Ucebna.Ucebna;
import com.example.Rozvrh.Ucitel.Ucitel;

import java.time.LocalTime;

public class Rozvrh {
    public Long id;
    private Long predmetId;
    private Long ucitelId;
    private Long ucebnaId;
    private Predmet predmet;
    private Ucitel  ucitel;
    private Ucebna  ucebna;
    private Den den;
    private LocalTime start;
    private LocalTime finish;

    public void setPredmetId(Long predmetId) {
        this.predmetId = predmetId;
    }

    public void setUcitelId(Long ucitelId) {
        this.ucitelId = ucitelId;
    }

    public void setUcebnaId(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
    }

    public Long getPredmetId() {
        return predmetId;
    }

    public Long getUcitelId() {
        return ucitelId;
    }

    public Long getUcebnaId() {
        return ucebnaId;
    }
}

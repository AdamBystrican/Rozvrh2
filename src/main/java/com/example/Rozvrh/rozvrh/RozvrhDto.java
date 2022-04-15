package com.example.Rozvrh.rozvrh;

import com.example.Rozvrh.Den;

import java.time.LocalTime;

public class RozvrhDto {
    public Long id;
    private Long predmetId;
    private Long ucitelId;
    private Long ucebnaId;
    String predmet;
    String ucitel;
    String ucebna;
    private Den den;
    private LocalTime start;
    private LocalTime finish;

    public boolean kontrolaStartEnd(){
        if(this.start.isAfter(this.finish))
            return false;
        return true;
    }
    /*
    public boolean kontrolaCasu(LocalTime start, LocalTime finish){
        if(start.isBefore(this.start) && finish.isBefore(this.start))
            return true;
        if(start.isAfter(this.finish))
            return true;
        return false;
    }*/


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Den getDen() {
        return den;
    }

    public void setDen(Den den) {
        this.den = den;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public void setUcitel(String ucitel) {
        this.ucitel = ucitel;
    }

    public void setUcebna(String ucebna) {
        this.ucebna = ucebna;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setFinish(LocalTime finish) {
        this.finish = finish;
    }

    public String getPredmet() {
        return predmet;
    }

    public String getUcitel() {
        return ucitel;
    }

    public String getUcebna() {
        return ucebna;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getFinish() {
        return finish;
    }
}

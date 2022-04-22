package com.example.Rozvrh.rozvrh;

import com.example.Rozvrh.Den;
import com.example.Rozvrh.Predmet.PredmetEntity;
import com.example.Rozvrh.Ucebna.UcebnaEntity;
import com.example.Rozvrh.Ucitel.UcitelEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalTime;

@Entity
public class RozvrhEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private PredmetEntity predmet;
    @ManyToOne
    private UcitelEntity ucitel;
    @ManyToOne
    private UcebnaEntity ucebna;

    private int den;
    private String trieda;
    private LocalTime start;
    private LocalTime finish;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrieda() {
        return trieda;
    }

    public void setTrieda(String trieda) {
        this.trieda = trieda;
    }

    public int getDen() {
        return den;
    }

    public void setDen(int den) {
        this.den = den;
    }

    public void setPredmet(PredmetEntity predmet) {
        this.predmet = predmet;
    }

    public void setUcitel(UcitelEntity ucitel) {
        this.ucitel = ucitel;
    }

    public void setUcebna(UcebnaEntity ucebna) {
        this.ucebna = ucebna;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setFinish(LocalTime finish) {
        this.finish = finish;
    }

    public PredmetEntity getPredmet() {
        return predmet;
    }

    public UcitelEntity getUcitel() {
        return ucitel;
    }

    public UcebnaEntity getUcebna() {
        return ucebna;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getFinish() {
        return finish;
    }
}

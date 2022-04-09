package com.example.Rozvrh.Ucebna;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UcebnaEntity {
    @Id
    @GeneratedValue
    public Long id;
    String nazov;
    boolean maPocitace;
    String adresa;

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public void setMaPocitace(boolean maPocitace) {
        this.maPocitace = maPocitace;
    }

    public String getNazov() {
        return nazov;
    }

    public boolean isMaPocitace() {
        return maPocitace;
    }
}

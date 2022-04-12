package com.example.Rozvrh.Den;

import com.example.Rozvrh.Cas.Cas;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DenEntity {
    public Long id;
    private String nazov;
    private List<Cas> cas;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public void setCas(int zacHod, int zacMin, int konHod, int konMin) {
        if(this.cas == null)
            this.cas = new ArrayList<>();
        Cas pomCas = new Cas(zacHod,zacMin,konHod,konMin);
        this.cas.add(pomCas);
    }

    public Long getId() {
        return id;
    }

    public String getNazov() {
        return nazov;
    }

    public List<Cas> getCas() {
        return cas;
    }
}


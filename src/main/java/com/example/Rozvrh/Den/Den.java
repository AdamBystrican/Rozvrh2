package com.example.Rozvrh.Den;

import com.example.Rozvrh.Cas.Cas;

import java.util.ArrayList;
import java.util.List;

public class Den {
    public Long id;
    private String nazov;
    private List<Cas> cas;

    public Den() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public void setCas(List<Cas> cas) {
        this.cas = cas;
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

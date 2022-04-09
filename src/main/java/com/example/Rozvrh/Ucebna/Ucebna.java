package com.example.Rozvrh.Ucebna;

public class Ucebna {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public void setMaPocitace(boolean maPocitace) {
        this.maPocitace = maPocitace;
    }

    public Long getId() {
        return id;
    }

    public String getNazov() {
        return nazov;
    }

    public boolean isMaPocitace() {
        return maPocitace;
    }


    public Ucebna() {
    }
}

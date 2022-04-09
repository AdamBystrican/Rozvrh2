package com.example.Rozvrh.Ucebna;

public class UcebnaDto {
    String nazov;
    boolean maPocitace;
    String adresa;

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
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

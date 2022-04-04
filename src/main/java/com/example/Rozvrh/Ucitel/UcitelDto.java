package com.example.Rozvrh.Ucitel;

public class UcitelDto {
    private String meno;
    private String priezvisko;
    private String kontakt;

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public String getMeno() {
        return meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public String getKontakt() {
        return kontakt;
    }
}

package com.example.Rozvrh.Ucitel;

public class Ucitel {
    public Long id;
    private String meno;
    private String priezvisko;
    private String kontakt;

    public Ucitel(Long id) {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public Long getId() {
        return id;
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

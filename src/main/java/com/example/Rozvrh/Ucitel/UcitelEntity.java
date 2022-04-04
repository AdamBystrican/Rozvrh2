package com.example.Rozvrh.Ucitel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UcitelEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String meno;
    private String priezvisko;
    private String kontakt;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

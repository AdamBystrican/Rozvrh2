package com.example.Rozvrh.Cas;

import java.sql.Time;
import java.time.LocalTime;
public class Cas {
    private Time zaciatok;
    private Time koniec;

    public Time getZaciatok() {
        return zaciatok;
    }

    public Time getKoniec() {
        return koniec;
    }

    public void setZaciatok(Time zaciatok) {
        this.zaciatok = zaciatok;
    }

    public void setKoniec(Time koniec) {
        this.koniec = koniec;
    }

    public Cas(String zaciatok, String koniec) {
        this.zaciatok.valueOf(zaciatok);
        this.koniec.valueOf(koniec);
    }
}

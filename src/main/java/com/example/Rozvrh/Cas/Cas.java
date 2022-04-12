package com.example.Rozvrh.Cas;


public class Cas {
    private int zacHod;
    private int zacMin;
    private int konHod;
    private int konMin;

    public Cas(int zacHod, int zacMin, int konHod, int konMin) {
        this.zacHod = zacHod;
        this.zacMin = zacMin;
        this.konHod = konHod;
        this.konMin = konMin;
    }

    public void setZacHod(int zacHod) {
        this.zacHod = zacHod;
    }

    public void setZacMin(int zacMin) {
        this.zacMin = zacMin;
    }

    public void setKonHod(int konHod) {
        this.konHod = konHod;
    }

    public void setKonMin(int konMin) {
        this.konMin = konMin;
    }

    public int getZacHod() {
        return zacHod;
    }

    public int getZacMin() {
        return zacMin;
    }

    public int getKonHod() {
        return konHod;
    }

    public int getKonMin() {
        return konMin;
    }
}

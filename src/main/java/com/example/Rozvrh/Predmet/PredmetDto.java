package com.example.Rozvrh.Predmet;

public class PredmetDto {
    private String name;
    private boolean computersRequired;
    private String type;


    public void setName(String name) {
        this.name = name;
    }

    public void setComputersRequired(boolean computersRequired) {
        this.computersRequired = computersRequired;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isComputersRequired() {
        return computersRequired;
    }

    public String getType() {
        return type;
    }
}

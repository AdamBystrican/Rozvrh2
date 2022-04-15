package com.example.Rozvrh.Predmet;


public class Predmet {

    public Long id;
    private String name;
    private boolean computersRequired;
    private String type;

    public Predmet() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComputersRequired(boolean computersRequired) {
        this.computersRequired = computersRequired;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
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

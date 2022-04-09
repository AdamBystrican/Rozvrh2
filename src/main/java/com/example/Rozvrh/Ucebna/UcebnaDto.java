package com.example.Rozvrh.Ucebna;

public class UcebnaDto {
    String name;
    boolean computersProviding;
    String address;

    public void setName(String name) {
        this.name = name;
    }

    public void setComputersProviding(boolean computersProviding) {
        this.computersProviding = computersProviding;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public boolean isComputersProviding() {
        return computersProviding;
    }

    public String getAddress() {
        return address;
    }
}

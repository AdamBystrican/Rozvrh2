package com.example.Rozvrh.Classroom;

public class Classroom {
    public Long id;
    String name;
    boolean computersProviding;
    String address;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComputersProviding(boolean computersProviding) {
        this.computersProviding = computersProviding;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
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

    public Classroom() {
    }
}

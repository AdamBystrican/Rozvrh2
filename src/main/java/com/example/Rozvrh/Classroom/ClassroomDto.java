package com.example.Rozvrh.Classroom;

public class ClassroomDto {
    private String name;
    boolean computersProviding;
    private String address;
    private Long id;

    public Long getId() {
        return id;
    }

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

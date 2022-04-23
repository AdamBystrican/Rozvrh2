package com.example.Rozvrh.Teacher;

public class Teacher {
    public Long id;
    private String firstName;
    private String lastName;
    private String contact;

    public Teacher(){

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContact() {
        return contact;
    }
}

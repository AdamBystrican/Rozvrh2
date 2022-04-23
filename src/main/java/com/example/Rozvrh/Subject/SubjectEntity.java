package com.example.Rozvrh.Subject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SubjectEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private boolean computersRequired;
    private String type;



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

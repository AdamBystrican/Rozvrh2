package com.example.Rozvrh.Timeblock.service;

import com.example.Rozvrh.Classroom.Classroom;
import com.example.Rozvrh.Group.Group;
import com.example.Rozvrh.Subject.Subject;
import com.example.Rozvrh.Teacher.Teacher;

public class TimeblockCreateDto {
    public Long id;
    private Subject subject;
    private Group group;
    private Teacher teacher;
    private Classroom classroom;
    private int day;
    private String start;
    private String finish;

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }
}

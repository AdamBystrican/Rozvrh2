package com.example.Rozvrh.Timeblock;

import com.example.Rozvrh.Group.Group;

import java.time.LocalTime;

public class TimeblockDto {
    public Long id;
    private Long subjectId;
    private Long teacherId;
    private Long classroomId;
    private Long groupId;
    private String subject;
    private String teacher;
    private String classroom;
    private int day;
    private String group;
    private LocalTime start;
    private LocalTime finish;

    public boolean StartFinishCheck(){
        if(this.start.isAfter(this.finish))
            return false;
        return true;
    }

    public boolean TimeCheck(LocalTime start, LocalTime finish){
        if(this.finish.isBefore(start))
            return true;
        if(this.start.isAfter(finish))
            return true;
        return false;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setFinish(LocalTime finish) {
        this.finish = finish;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getClassroom() {
        return classroom;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getFinish() {
        return finish;
    }
}

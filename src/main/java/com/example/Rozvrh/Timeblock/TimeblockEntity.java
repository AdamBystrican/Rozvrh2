package com.example.Rozvrh.Timeblock;

import com.example.Rozvrh.Group.GroupEntity;
import com.example.Rozvrh.Subject.SubjectEntity;
import com.example.Rozvrh.Classroom.ClassroomEntity;
import com.example.Rozvrh.Teacher.TeacherEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalTime;

@Entity
public class TimeblockEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private SubjectEntity subject;
    @ManyToOne
    private TeacherEntity teacher;
    @ManyToOne
    private ClassroomEntity classroom;
    @ManyToOne
    private GroupEntity group;

    private int day;
    private LocalTime start;
    private LocalTime finish;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public void setClassroom(ClassroomEntity classroom) {
        this.classroom = classroom;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setFinish(LocalTime finish) {
        this.finish = finish;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public ClassroomEntity getClassroom() {
        return classroom;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getFinish() {
        return finish;
    }
}

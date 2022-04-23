package com.example.Rozvrh.Timeblock;

import com.example.Rozvrh.Group.Group;
import com.example.Rozvrh.Subject.Subject;
import com.example.Rozvrh.Classroom.Classroom;
import com.example.Rozvrh.Teacher.Teacher;

import java.time.LocalTime;

public class Timeblock {
    public Long id;
    private Long subjectId;
    private Long teacherId;
    private Long classroomId;
    private Long groupId;
    private Subject subject;
    private Teacher teacher;
    private Classroom classroom;
    private int den;
    private Group group;
    private LocalTime start;
    private LocalTime finish;

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public Long getGroupId() {
        return groupId;
    }
}

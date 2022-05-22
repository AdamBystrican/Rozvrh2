package com.example.Rozvrh.Teacher;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public String createTeacher(@RequestBody TeacherDto teacherDto){
        return teacherService.createTeacher(teacherDto);
    }
    @GetMapping("/{teacherId}")
    public TeacherDto getTeacher(@PathVariable Long teacherId){
        return teacherService.getTeacher(teacherId);
    }
    @GetMapping
    public List<TeacherDto> getTeachers(@RequestParam(required = false)String teacherName){
        return teacherService.getTeachers(teacherName);
    }
    @PutMapping("/{teacherId}")
    public String updateTeacher(@PathVariable Long teacherId, @RequestBody TeacherDto teacherDto){
        return teacherService.updateTeacher(teacherId, teacherDto);
    }
    @DeleteMapping("/{teacherId}")
    public void deleteTeacher(@PathVariable Long teacherId){
        teacherService.deleteTeacher(teacherId);
    }
}

package com.example.Rozvrh.Classroom;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/classroom")
public class ClassroomController {
    private ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }
    @PostMapping
    public Long createClassroom(@RequestBody ClassroomDto classroomDto){
        return classroomService.createClassroom(classroomDto);
    }
    @GetMapping
    public List<ClassroomDto> getClassrooms(@RequestParam(required = false) String classroomName){
        return classroomService.getClassrooms(classroomName);
    }
    @GetMapping("/{classroomId}")
    public ClassroomDto getClassroom(@PathVariable Long classroomId){
        return classroomService.getClassroom(classroomId);
    }
    @PutMapping("/{classroomId}")
    public void updateClassroom(@PathVariable Long classroomId, @RequestBody ClassroomDto classroomDto){
        classroomService.updateClassroom(classroomId, classroomDto);
    }
    @DeleteMapping("/{classroomId}")
    public void deleteClassroom(@PathVariable Long classroomId){
        classroomService.deleteClassroom(classroomId);
    }
}

package com.example.Rozvrh.Subject;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subject")
public class SubjectController {
    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public String createSubject(@RequestBody SubjectDto subjectDto){
        return subjectService.createSubject(subjectDto);
    }
    @GetMapping
    public List<SubjectDto> getSubjects(@RequestParam(required = false) String SubjectName){
        return subjectService.getSubjects(SubjectName);
    }
    @GetMapping("/{subjectId}")
    public SubjectDto getSubject(@PathVariable Long subjectId){
        return subjectService.getSubject(subjectId);
    }
    @PutMapping("/{subjectId}")
    public String updateSubject(@PathVariable Long subjectId, @RequestBody SubjectDto subjectDto){
        return subjectService.updateSubject(subjectId,subjectDto);
    }
    @DeleteMapping("/{subjectId}")
    public void deleteSubject(@PathVariable Long subjectId){
        subjectService.deleteSubject(subjectId);
    }
}

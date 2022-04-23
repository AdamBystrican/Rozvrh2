package com.example.Rozvrh.Subject;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    private static SubjectDto mapToSubjectDto(SubjectEntity subjectEntity){
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName(subjectEntity.getName());
        subjectDto.setComputersRequired(subjectEntity.isComputersRequired());
        subjectDto.setType(subjectEntity.getType());
        subjectDto.setId(subjectEntity.getId());
        return subjectDto;
    }

    @Transactional
    public Long createSubject(SubjectDto subjectDto){
        SubjectEntity se = new SubjectEntity();
        se.setName(subjectDto.getName());
        se.setComputersRequired(subjectDto.isComputersRequired());
        se.setType(subjectDto.getType());
        this.subjectRepository.save(se);
        return se.getId();
    }

    @Transactional
    public SubjectDto getSubject(Long subjectId){
        Optional<SubjectEntity> byId =subjectRepository.findById(subjectId);
            if(byId.isPresent()){
                return mapToSubjectDto(byId.get());
            }
            return null;
    }

   @Transactional
   public List<SubjectDto> getSubjects(String SubjectName){
       List<SubjectDto> subjects = new LinkedList<>();
       for(SubjectEntity s1 : subjectRepository.findAll()){
            SubjectDto s2 = mapToSubjectDto(s1);
            subjects.add(s2);
       }
       return subjects;
   }

   @Transactional
   public void updateSubject(Long subjectId, SubjectDto subjectDto){
        Optional<SubjectEntity> byId = subjectRepository.findById(subjectId);
        if(byId.isPresent()){
            byId.get().setName(subjectDto.getName());
            byId.get().setComputersRequired(subjectDto.isComputersRequired());
            byId.get().setType(subjectDto.getType());
        }
   }
    @Transactional
    public void deleteSubject(Long subjectId){
        subjectRepository.deleteById(subjectId);
    }


}

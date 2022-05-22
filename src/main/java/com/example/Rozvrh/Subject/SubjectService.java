package com.example.Rozvrh.Subject;

import com.example.Rozvrh.Group.GroupEntity;
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
    public String createSubject(SubjectDto subjectDto){
        for(SubjectEntity s1 : subjectRepository.findAll()){
            if(s1.getName().equals(subjectDto.getName()))
                return "Subject with this name already exists";
        }
        SubjectEntity se = new SubjectEntity();
        se.setName(subjectDto.getName());
        se.setComputersRequired(subjectDto.isComputersRequired());
        se.setType(subjectDto.getType());
        this.subjectRepository.save(se);
        return se.getId().toString();
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
   public String updateSubject(Long subjectId, SubjectDto subjectDto){
       for(SubjectEntity s1 : subjectRepository.findAll()){
           if(s1.getName().equals(subjectDto.getName()))
               return "Subject with this name already exists";
       }
        Optional<SubjectEntity> byId = subjectRepository.findById(subjectId);
        if(byId.isPresent()){
            byId.get().setName(subjectDto.getName());
            byId.get().setComputersRequired(subjectDto.isComputersRequired());
            byId.get().setType(subjectDto.getType());
            return byId.get().getId().toString();
        }
        return "Wrong Id";
   }
    @Transactional
    public void deleteSubject(Long subjectId){
        subjectRepository.deleteById(subjectId);
    }


}

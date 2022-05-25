package com.example.Rozvrh.Classroom;

import com.example.Rozvrh.Group.GroupEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {
    private ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    private static ClassroomDto mapToClassroomDto(ClassroomEntity classroomEntity){
        ClassroomDto classroomDto = new ClassroomDto();
        classroomDto.setName(classroomEntity.getName());
        classroomDto.setComputersProviding(classroomEntity.isComputersProviding());
        classroomDto.setAddress(classroomEntity.getAddress());
        classroomDto.setId(classroomEntity.getId());
        return classroomDto;
    }

    @Transactional
    public String createClassroom(ClassroomDto classroomDto){
        for(ClassroomEntity c1 : classroomRepository.findAll()){
            if(c1.getName().equals(classroomDto.getName()))
                return "Classroom with this name already exists";
        }
        ClassroomEntity ue = new ClassroomEntity();
        ue.setName(classroomDto.getName());
        ue.setComputersProviding(classroomDto.isComputersProviding());
        ue.setAddress(classroomDto.getAddress());
        classroomRepository.save(ue);
        return ue.getId().toString();
    }
    @Transactional
    public ClassroomDto getClassroom(Long classroomId){
        Optional<ClassroomEntity> byId = classroomRepository.findById(classroomId);
        if(byId.isPresent()){
            return mapToClassroomDto(byId.get());
        }
        return null;
    }

    @Transactional
    public List<ClassroomDto> getClassrooms(String ClassroomName){
        List<ClassroomDto> classrooms = new LinkedList<>();
        for(ClassroomEntity c1 : classroomRepository.findAll()){
            ClassroomDto c2 = mapToClassroomDto(c1);
            classrooms.add(c2);
        }
        return classrooms;
    }
    //neporovnava sa sam so sebou
    @Transactional
    public String updateClassroom(Long classroomId, ClassroomDto classroomDto){
        for(ClassroomEntity c1 : classroomRepository.findAll()){
            if(c1.getName().equals(classroomDto.getName()) && c1.getId() != classroomId)
                return "Classroom with this name already exists";
        }
        Optional<ClassroomEntity> byId = classroomRepository.findById(classroomId);
        if(byId.isPresent()){
            byId.get().setName(classroomDto.getName());
            byId.get().setComputersProviding(classroomDto.isComputersProviding());
            byId.get().setAddress(classroomDto.getAddress());
            return byId.get().getId().toString();
        }
        return "Wrong Id";
    }

    @Transactional
    public void deleteClassroom(Long classroomId){
        classroomRepository.deleteById(classroomId);
    }
}

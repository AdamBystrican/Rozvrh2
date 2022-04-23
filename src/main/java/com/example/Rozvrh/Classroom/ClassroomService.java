package com.example.Rozvrh.Classroom;

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
    public Long createClassroom(ClassroomDto classroomDto){
        ClassroomEntity ue = new ClassroomEntity();
        ue.setName(classroomDto.getName());
        ue.setComputersProviding(classroomDto.isComputersProviding());
        ue.setAddress(classroomDto.getAddress());
        classroomRepository.save(ue);
        return ue.getId();
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

    @Transactional
    public void updateClassroom(Long classroomId, ClassroomDto classroomDto){
        Optional<ClassroomEntity> byId = classroomRepository.findById(classroomId);
        if(byId.isPresent()){
            byId.get().setName(classroomDto.getName());
            byId.get().setComputersProviding(classroomDto.isComputersProviding());
            byId.get().setAddress(classroomDto.getAddress());
        }
    }

    @Transactional
    public void deleteClassroom(Long classroomId){
        classroomRepository.deleteById(classroomId);
    }
}

package com.example.Rozvrh.Teacher;

import com.example.Rozvrh.Group.GroupEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    private static TeacherDto mapToTeacherDto(TeacherEntity teacherEntity){
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setFirstName(teacherEntity.getFirstName());
        teacherDto.setLastName(teacherEntity.getLastName());
        teacherDto.setContact(teacherEntity.getContact());
        teacherDto.setId(teacherEntity.getId());
        return teacherDto;
    }

    @Transactional
    public String createTeacher(TeacherDto teacherDto){
        for(TeacherEntity t1 : teacherRepository.findAll()){
            if(t1.getFullName().equals(teacherDto.getFirstName() + " " + teacherDto.getLastName()))
                return "Teacher with this name already exists";
        }
        TeacherEntity te = new TeacherEntity();
        te.setFirstName(teacherDto.getFirstName());
        te.setLastName(teacherDto.getLastName());
        te.setContact(teacherDto.getContact());
        this.teacherRepository.save(te);
        return te.getId().toString();
    }

    @Transactional
    public TeacherDto getTeacher(Long teacherId){
        Optional<TeacherEntity> byId = this.teacherRepository.findById(teacherId);
        if(byId.isPresent()){
            return mapToTeacherDto(byId.get());
        }
        return null;
    }

    @Transactional
    public List<TeacherDto> getTeachers(String teacherName){
        List<TeacherDto> teachers = new LinkedList<>();
        for(TeacherEntity t1 : teacherRepository.findAll()){
            TeacherDto t2 = mapToTeacherDto(t1);
            teachers.add(t2);
        }
        return teachers;
    }
    //neporovnava sa sam so sebou
    @Transactional
    public String updateTeacher(Long teacherId, TeacherDto teacherDto){
        for(TeacherEntity t1 : teacherRepository.findAll()){
            if(t1.getFullName().equals(teacherDto.getFirstName() + " " + teacherDto.getLastName()) && t1.getId() != teacherId)
                return "Teacher with this name already exists";
        }
        Optional<TeacherEntity> byId = teacherRepository.findById(teacherId);
        if(byId.isPresent()){
            byId.get().setFirstName(teacherDto.getFirstName());
            byId.get().setLastName(teacherDto.getLastName());
            byId.get().setContact(teacherDto.getContact());
            return byId.get().toString();
        }
        return "Wrong Id";
    }

    @Transactional
    public void deleteTeacher(Long teacherId){
        teacherRepository.deleteById(teacherId);
    }
}

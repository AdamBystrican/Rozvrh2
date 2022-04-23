package com.example.Rozvrh.Teacher;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends CrudRepository<TeacherEntity, Long> {
    @Override
    List<TeacherEntity> findAll();

}

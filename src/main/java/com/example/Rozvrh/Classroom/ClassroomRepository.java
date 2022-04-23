package com.example.Rozvrh.Classroom;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends CrudRepository<ClassroomEntity, Long> {
    @Override
    List<ClassroomEntity> findAll();
}

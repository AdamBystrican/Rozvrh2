package com.example.Rozvrh.Subject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends CrudRepository<SubjectEntity, Long> {
    @Override
    List<SubjectEntity> findAll();
}

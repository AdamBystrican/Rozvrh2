package com.example.Rozvrh.Timeblock;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeblockRepository extends CrudRepository<TimeblockEntity, Long> {
    @Override
    List<TimeblockEntity> findAll();
}

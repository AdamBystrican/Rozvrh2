package com.example.Rozvrh.Den;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DenRepository extends CrudRepository<DenEntity, Long> {
    @Override
    List<DenEntity> findAll();
}

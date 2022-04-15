package com.example.Rozvrh.rozvrh;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RozvrhRepository extends CrudRepository<RozvrhEntity, Long> {
    @Override
    List<RozvrhEntity> findAll();
}

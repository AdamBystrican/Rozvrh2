package com.example.Rozvrh.Ucebna;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UcebnaRepository extends CrudRepository<UcebnaEntity, Long> {
    @Override
    List<UcebnaEntity> findAll();
}

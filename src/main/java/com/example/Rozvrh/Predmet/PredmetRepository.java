package com.example.Rozvrh.Predmet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredmetRepository extends CrudRepository<PredmetEntity, Long> {
    @Override
    List<PredmetEntity> findAll();
}

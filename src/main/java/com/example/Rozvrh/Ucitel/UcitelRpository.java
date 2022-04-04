package com.example.Rozvrh.Ucitel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UcitelRpository extends CrudRepository<UcitelEntity, Long> {
    @Override
    List<UcitelEntity> findAll();

}

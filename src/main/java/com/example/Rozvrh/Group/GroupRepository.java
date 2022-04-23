package com.example.Rozvrh.Group;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
    @Override
    List<GroupEntity> findAll();
}

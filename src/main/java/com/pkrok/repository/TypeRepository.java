package com.pkrok.repository;

import com.pkrok.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Long> {
    List<TypeEntity> findAllByOrderById();

    TypeEntity findByName(String name);
}

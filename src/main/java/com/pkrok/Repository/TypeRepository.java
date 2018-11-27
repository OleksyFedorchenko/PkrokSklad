package com.pkrok.Repository;

import com.pkrok.Entity.PartEntity;
import com.pkrok.Entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Long> {
    List<TypeEntity> findAllByOrderById();
    TypeEntity findByName(String name);
}

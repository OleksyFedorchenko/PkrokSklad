package com.pkrok.Repository;

import com.pkrok.Entity.FirmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirmRepository extends JpaRepository<FirmEntity, Long> {
    List<FirmEntity> findAllByOrderById();

    FirmEntity findByName(String name);
}

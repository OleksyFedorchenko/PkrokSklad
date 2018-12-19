package com.pkrok.repository;

import com.pkrok.entity.PartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<PartEntity, Long> {

    //Основний метод для виводу всіх запчастин за фірмою і типом.
    @Query("SELECT p FROM PartEntity p JOIN p.firm pf JOIN p.type pt WHERE pf.name=:firmName AND pt.name=:typeName")
    List<PartEntity> findByFirmAndType(@Param("firmName") String firmName, @Param("typeName") String typeName);

    List<PartEntity> findByNameContaining(String name);

    PartEntity findByName(String name);

    List<PartEntity> findAllByOrderByNameAsc();

    List<PartEntity> findAllByOrderByQuantityAsc();

    @Query("SELECT p FROM PartEntity p JOIN p.firm pf JOIN p.type pt WHERE (pf.name=:firmName AND pt.name=:typeName AND p.name LIKE :search) ORDER BY p.name")
    List<PartEntity> findByFirmAndTypeOrdNameLike(@Param("firmName") String firmName, @Param("typeName") String typeName, @Param("search") String search);

    @Query("SELECT p FROM PartEntity p JOIN p.firm pf JOIN p.type pt WHERE (pf.name=:firmName AND pt.name=:typeName AND p.name LIKE :search) ORDER BY p.quantity DESC")
    List<PartEntity> findByFirmAndTypeOrdQuantityLike(@Param("firmName") String firmName, @Param("typeName") String typeName, @Param("search") String search);

}


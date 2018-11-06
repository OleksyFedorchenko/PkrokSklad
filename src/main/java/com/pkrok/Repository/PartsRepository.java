package com.pkrok.Repository;

import com.pkrok.entity.PartsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartsRepository extends JpaRepository<PartsEntity,Long> {

    //Основний метод для виводу всіх запчастин за фірмою і типом.
    @Query("SELECT p FROM PartsEntity p JOIN p.firm pf JOIN p.type pt WHERE pf.name=:firmName AND pt.name=:typeName")
    List<PartsEntity> findByFirmAndType(@Param("firmName")String firmName, @Param("typeName") String typeName);
}

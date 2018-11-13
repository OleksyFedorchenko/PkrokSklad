package com.pkrok.Service;

import com.pkrok.Domain.PartsDTO;

import java.util.List;

public interface PartService {
    void addPart(PartsDTO partDTO);

    List<PartsDTO> findAllParts();

    List<PartsDTO> findByFirmAndType(String firmName, String typeName);

    List<PartsDTO> findByNameContains(String name);

    List<PartsDTO> findAllPartsOrderByName();

    List<PartsDTO> findAllPartsOrderByQuantity();

    List<PartsDTO> findByFirmAndTypeOrdName(String firmName, String typeName);

    List<PartsDTO> findByFirmAndTypeOrdQuantity(String firmName, String typeName);

    List<PartsDTO> findByFirmAndTypeOrdNameLike(String firmName, String typeName,String search);

    List<PartsDTO> findByFirmAndTypeOrdQuantityLike(String firmName, String typeName,String search);
}

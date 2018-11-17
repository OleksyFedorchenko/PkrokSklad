package com.pkrok.Service;

import com.pkrok.Domain.PartsDTO;

import java.util.List;

public interface PartService {
    void addPart(PartsDTO partDTO);

    List<PartsDTO> findAllParts();

    List<PartsDTO> findByFirmAndType(String firmName, String typeName);

    List<PartsDTO> findByNameContains(String name);

    List<PartsDTO> findAllPartsOrderByName();

    void deletePartById(Long id);

    List<PartsDTO> findAllPartsOrderByQuantity();

    List<PartsDTO> findByFirmAndTypeOrdNameLike(String firmName, String typeName, String search);

    List<PartsDTO> findByFirmAndTypeOrdQuantityLike(String firmName, String typeName, String search);
}

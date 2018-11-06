package com.pkrok.Service;

import com.pkrok.domain.PartsDTO;

import java.util.List;

public interface PartsService {
    void addPart(PartsDTO partDTO);
    List<PartsDTO> findAllParts();
    List<PartsDTO> findByFirmAndType(String firmName,String typeName);
}

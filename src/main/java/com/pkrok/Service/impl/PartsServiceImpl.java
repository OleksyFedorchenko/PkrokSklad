package com.pkrok.Service.impl;

import com.pkrok.Repository.PartsRepository;
import com.pkrok.Service.PartsService;
import com.pkrok.domain.PartsDTO;
import com.pkrok.entity.PartsEntity;
import com.pkrok.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartsServiceImpl implements PartsService {
    @Autowired
    private PartsRepository partsRepository;
    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void addPart(PartsDTO partDTO) {
    PartsEntity part = modelMapper.map(partDTO,PartsEntity.class);
    partsRepository.save(part);
    }

    @Override
    public List<PartsDTO> findAllParts() {
        List<PartsEntity> partsEntities=partsRepository.findAll();
        List<PartsDTO> partsDTOS = modelMapper.mapAll(partsEntities,PartsDTO.class);
        return partsDTOS;
    }

    @Override
    public List<PartsDTO> findByFirmAndType(String firmName, String typeName) {
        List<PartsEntity> partsEntities=partsRepository.findByFirmAndType(firmName, typeName);
        List<PartsDTO> partsDTOS=modelMapper.mapAll(partsEntities,PartsDTO.class);
        return partsDTOS;
    }


}

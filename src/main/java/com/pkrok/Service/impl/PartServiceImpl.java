package com.pkrok.Service.impl;

import com.pkrok.Exceptions.AlreadyExistsException;
import com.pkrok.Repository.PartRepository;
import com.pkrok.Service.PartService;
import com.pkrok.Domain.PartsDTO;
import com.pkrok.Entity.PartEntity;
import com.pkrok.Utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartServiceImpl implements PartService {
    private PartRepository partRepository;
    private ObjectMapperUtils modelMapper;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, ObjectMapperUtils modelMapper) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addPart(PartsDTO partDTO) {
        PartEntity partEntity = partRepository.findByName(partDTO.getName());
        if (partEntity != null) {
            throw new AlreadyExistsException("Product with name [" + partDTO.getName() + "]already exists");
        }
        PartEntity part = modelMapper.map(partDTO, PartEntity.class);
        partRepository.save(part);
    }

    @Override
    public List<PartsDTO> findAllParts() {
        List<PartEntity> partsEntities = partRepository.findAll();
        return modelMapper.mapAll(partsEntities, PartsDTO.class);
    }

    @Override
    public List<PartsDTO> findByFirmAndType(String firmName, String typeName) {
        List<PartEntity> partsEntities = partRepository.findByFirmAndType(firmName, typeName);
        return modelMapper.mapAll(partsEntities, PartsDTO.class);
    }

    @Override
    public List<PartsDTO> findByNameContains(String name) {
        List<PartEntity> partEntities = partRepository.findByNameContaining(name);
        return modelMapper.mapAll(partEntities, PartsDTO.class);
    }

    @Override
    public List<PartsDTO> findAllPartsOrderByName() {
        List<PartEntity> partEntities = partRepository.findAllOrderByName();
        return modelMapper.mapAll(partEntities, PartsDTO.class);
    }

    @Override
    public List<PartsDTO> findAllPartsOrderByQuantity() {
        List<PartEntity> partEntities = partRepository.findAllOrderByQuantity();
        return modelMapper.mapAll(partEntities, PartsDTO.class);
    }


}

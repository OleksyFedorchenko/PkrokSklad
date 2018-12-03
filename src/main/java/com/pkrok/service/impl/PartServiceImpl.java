package com.pkrok.service.impl;

import com.pkrok.domain.PartsDTO;
import com.pkrok.entity.PartEntity;
import com.pkrok.exceptions.AlreadyExistsException;
import com.pkrok.exceptions.ResourceNotFoundException;
import com.pkrok.repository.PartRepository;
import com.pkrok.service.PartService;
import com.pkrok.utils.ObjectMapperUtils;
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
            throw new AlreadyExistsException("Part with name [" + partDTO.getName() + "]already exists");
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
        List<PartEntity> partEntities = partRepository.findAllByOrderByNameAsc();
        return modelMapper.mapAll(partEntities, PartsDTO.class);
    }

    @Override
    public PartsDTO findPartById(Long id) {
        PartEntity partEntity = partRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not delete product with id[" + id + "]not found"));
        return modelMapper.map(partEntity, PartsDTO.class);
    }

    @Override
    public void setPartById(PartsDTO partDTO) {
        PartEntity part = partRepository.findById(partDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Could not edit this id not found"));
        part.setName(partDTO.getName());
        part.setQuantity(partDTO.getQuantity());
        part.setPlace(partDTO.getPlace());
        part.setDescription(partDTO.getDescription());
        part.setFirm(partDTO.getFirm());
        part.setType(partDTO.getType());
        part.setImage(partDTO.getImage());
        PartEntity part1 = partRepository.findByName(part.getName());
        if (part1 != null) {
            if (!part1.getName().equals(part.getName())) {
                throw new AlreadyExistsException("Part with name [" + part.getName() + "]already exists");
            }
        }
        part = modelMapper.map(partDTO, PartEntity.class);
        partRepository.save(part);
    }

    @Override
    public void deletePartById(Long id) {
        PartEntity partEntity = partRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not delete product with id[" + id + "]not found"));
        partRepository.deleteById(id);
    }

    @Override
    public List<PartsDTO> findAllPartsOrderByQuantity() {
        List<PartEntity> partEntities = partRepository.findAllByOrderByQuantityAsc();
        return modelMapper.mapAll(partEntities, PartsDTO.class);
    }

    @Override
    public List<PartsDTO> findByFirmAndTypeOrdNameLike(String firmName, String typeName, String search) {
        List<PartEntity> partsEntities = partRepository.findByFirmAndTypeOrdNameLike(firmName, typeName, search);
        return modelMapper.mapAll(partsEntities, PartsDTO.class);
    }

    @Override
    public List<PartsDTO> findByFirmAndTypeOrdQuantityLike(String firmName, String typeName, String search) {
        List<PartEntity> partsEntities = partRepository.findByFirmAndTypeOrdQuantityLike(firmName, typeName, search);
        return modelMapper.mapAll(partsEntities, PartsDTO.class);
    }

    @Override
    public void addImageToProduct(String image, Long id) {
        PartEntity partEntity = partRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product with id [" + id + "] not found")
                );
        partEntity.setImage(image);
        partRepository.save(partEntity);
    }

    @Override
    public void setQuantityById(Long id, int plus, int minus) {
        PartEntity partEntity = partRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Part with this id not found"));
        int quantityBeforeEdit = partEntity.getQuantity();
        partEntity.setQuantity(quantityBeforeEdit + plus - minus);
        partRepository.save(partEntity);
    }
}

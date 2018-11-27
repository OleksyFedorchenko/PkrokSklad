package com.pkrok.Service.impl;

import com.pkrok.Exceptions.AlreadyExistsException;
import com.pkrok.Exceptions.ResourceNotFoundException;
import com.pkrok.Repository.TypeRepository;
import com.pkrok.Service.TypeService;
import com.pkrok.Domain.TypeDTO;
import com.pkrok.Entity.TypeEntity;
import com.pkrok.Utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    private TypeRepository typeRepository;
    private ObjectMapperUtils modelMapper;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository, ObjectMapperUtils modelMapper) {
        this.typeRepository = typeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addType(TypeDTO typeDTO) {
        TypeEntity type = modelMapper.map(typeDTO, TypeEntity.class);
        typeRepository.save(type);
    }

    @Override
    public TypeDTO findTypeById(Long id) {
        TypeEntity typeEntity = typeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not delete type with id[" + id + "]not found"));
        return modelMapper.map(typeEntity, TypeDTO.class);
    }

    @Override
    public void setTypeById(TypeDTO typeDTO) {
        TypeEntity type = typeRepository.findById(typeDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Could not edit this id not found"));
        type.setName(typeDTO.getName());
        TypeEntity type1 = typeRepository.findByName(type.getName());
        if (type1 != null) {
            if (type1.getName() != type.getName()) {
                throw new AlreadyExistsException("Type with name [" + type.getName() + "]already exists");
            }
        }
        type = modelMapper.map(typeDTO, TypeEntity.class);
        typeRepository.save(type);
    }

    @Override
    public void deleteTypeById(Long id) {
        TypeEntity typeEntity = typeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not delete type with id[" + id + "]not found"));
        typeRepository.deleteById(id);
    }

    @Override
    public List<TypeDTO> findAllOrderById() {
        List<TypeEntity> typeEntities = typeRepository.findAllByOrderById();
        return modelMapper.mapAll(typeEntities, TypeDTO.class);
    }

    @Override
    public List<TypeDTO> findAllTypes() {
        List<TypeEntity> typeEntities = typeRepository.findAll();
        return modelMapper.mapAll(typeEntities, TypeDTO.class);
    }
}

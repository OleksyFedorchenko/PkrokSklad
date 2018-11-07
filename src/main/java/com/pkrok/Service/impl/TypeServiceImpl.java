package com.pkrok.Service.impl;

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
    public List<TypeDTO> findAllTypes() {
        List<TypeEntity> typeEntities = typeRepository.findAll();
        return modelMapper.mapAll(typeEntities, TypeDTO.class);
    }
}

package com.pkrok.Service.impl;

import com.pkrok.Repository.TypeRepository;
import com.pkrok.Service.TypeService;
import com.pkrok.domain.TypeDTO;
import com.pkrok.entity.TypeEntity;
import com.pkrok.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void addType(TypeDTO typeDTO) {
        TypeEntity type=modelMapper.map(typeDTO,TypeEntity.class);
        typeRepository.save(type);
    }

    @Override
    public List<TypeDTO> findAllTypes() {
        List<TypeEntity> typeEntities=typeRepository.findAll();
        List<TypeDTO> typeDTOS=modelMapper.mapAll(typeEntities,TypeDTO.class);
        return typeDTOS;
    }
}

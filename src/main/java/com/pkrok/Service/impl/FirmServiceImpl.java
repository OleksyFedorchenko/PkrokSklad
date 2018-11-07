package com.pkrok.Service.impl;

import com.pkrok.Repository.FirmRepository;
import com.pkrok.Service.FirmService;
import com.pkrok.Domain.FirmDTO;
import com.pkrok.Entity.FirmEntity;
import com.pkrok.Utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirmServiceImpl implements FirmService {
    private FirmRepository firmRepository;
    private ObjectMapperUtils modelMapper;

    @Autowired
    public FirmServiceImpl(FirmRepository firmRepository, ObjectMapperUtils modelMapper) {
        this.firmRepository = firmRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addFirm(FirmDTO firmDTO) {
        FirmEntity firm = modelMapper.map(firmDTO, FirmEntity.class);
        firmRepository.save(firm);
    }

    @Override
    public List<FirmDTO> findAllFirms() {
        List<FirmEntity> firmEntities = firmRepository.findAll();
        return modelMapper.mapAll(firmEntities, FirmDTO.class);
    }
}

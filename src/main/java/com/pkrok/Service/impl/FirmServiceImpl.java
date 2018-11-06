package com.pkrok.Service.impl;

import com.pkrok.Repository.FirmRepository;
import com.pkrok.Service.FirmService;
import com.pkrok.domain.FirmDTO;
import com.pkrok.entity.FirmEntity;
import com.pkrok.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FirmServiceImpl implements FirmService {
    @Autowired
    private FirmRepository firmRepository;
    @Autowired
    private ObjectMapperUtils modelMapper;
    @Override
    public void addFirm(FirmDTO firmDTO) {
        FirmEntity firm=modelMapper.map(firmDTO,FirmEntity.class);
        firmRepository.save(firm);
    }

    @Override
    public List<FirmDTO> findAllFirms() {
        List<FirmEntity> firmEntities=firmRepository.findAll();
        List<FirmDTO>  firmDTOS=modelMapper.mapAll(firmEntities,FirmDTO.class);
        return firmDTOS;
    }
}

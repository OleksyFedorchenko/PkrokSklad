package com.pkrok.Service.impl;

import com.pkrok.Exceptions.AlreadyExistsException;
import com.pkrok.Exceptions.ResourceNotFoundException;
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
    public List<FirmDTO> findAllOrderById() {
        List<FirmEntity> firmEntities = firmRepository.findAllByOrderById();
        return modelMapper.mapAll(firmEntities, FirmDTO.class);
    }

    @Override
    public List<FirmDTO> findAllFirms() {
        List<FirmEntity> firmEntities = firmRepository.findAll();
        return modelMapper.mapAll(firmEntities, FirmDTO.class);
    }

    @Override
    public void deleteFirmById(Long id) {
        FirmEntity firmEntity = firmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not delete product with id[" + id + "]not found"));
        firmRepository.deleteById(id);
    }

    @Override
    public FirmDTO findFirmById(Long id) {
        FirmEntity firmEntity = firmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not delete firm with id[" + id + "]not found"));
        return modelMapper.map(firmEntity, FirmDTO.class);
    }

    @Override
    public void setFirmById(FirmDTO firmDTO) {
        FirmEntity firm = firmRepository.findById(firmDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Could not edit this id not found"));
        firm.setName(firmDTO.getName());
        FirmEntity firm1 = firmRepository.findByName(firm.getName());
        if (firm1 != null) {
            if (firm1.getName() != firm.getName()) {
                throw new AlreadyExistsException("Firm with name [" + firm.getName() + "]already exists");
            }
        }
        firm = modelMapper.map(firmDTO, FirmEntity.class);
        firmRepository.save(firm);
    }
}

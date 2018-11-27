package com.pkrok.Service;

import com.pkrok.Domain.FirmDTO;

import java.util.List;

public interface FirmService {
    void addFirm(FirmDTO firmDTO);

    FirmDTO findFirmById(Long id);

    void setFirmById(FirmDTO firmDTO);

    void deleteFirmById(Long id);

    List<FirmDTO> findAllOrderById();

    List<FirmDTO> findAllFirms();
}

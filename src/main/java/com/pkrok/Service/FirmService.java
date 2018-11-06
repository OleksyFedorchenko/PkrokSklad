package com.pkrok.Service;

import com.pkrok.domain.FirmDTO;

import java.util.List;

public interface FirmService {
    void addFirm(FirmDTO firmDTO);
    List<FirmDTO> findAllFirms();
}

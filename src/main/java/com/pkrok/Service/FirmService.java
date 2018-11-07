package com.pkrok.Service;

import com.pkrok.Domain.FirmDTO;

import java.util.List;

public interface FirmService {
    void addFirm(FirmDTO firmDTO);

    List<FirmDTO> findAllFirms();
}

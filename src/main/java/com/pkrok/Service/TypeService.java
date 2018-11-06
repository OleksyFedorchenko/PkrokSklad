package com.pkrok.Service;

import com.pkrok.domain.TypeDTO;

import java.util.List;

public interface TypeService {
    void addType(TypeDTO typeDTO);
    List<TypeDTO> findAllTypes();
}

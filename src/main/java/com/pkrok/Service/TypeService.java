package com.pkrok.Service;

import com.pkrok.Domain.TypeDTO;

import java.util.List;

public interface TypeService {
    void addType(TypeDTO typeDTO);

    List<TypeDTO> findAllTypes();
}

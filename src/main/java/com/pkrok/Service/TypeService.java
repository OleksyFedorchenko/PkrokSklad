package com.pkrok.Service;

import com.pkrok.Domain.TypeDTO;

import java.util.List;

public interface TypeService {
    void addType(TypeDTO typeDTO);

    TypeDTO findTypeById(Long id);

    void setTypeById(TypeDTO typeDTO);

    void deleteTypeById(Long id);

    List<TypeDTO> findAllOrderById();

    List<TypeDTO> findAllTypes();
}

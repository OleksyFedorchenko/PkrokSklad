package com.pkrok.controller;

import com.pkrok.domain.ErrorDTO;
import com.pkrok.domain.TypeDTO;
import com.pkrok.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("types")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @PostMapping
    public ResponseEntity<?> addType(@Valid @RequestBody TypeDTO type, BindingResult br) {
        if (br.hasErrors()) {
            System.out.println("Validation error");
            String errMsg = br.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .findFirst().get().toString();
            ErrorDTO errorDTO = new ErrorDTO(errMsg);
            return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        }
        typeService.addType(type);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping("edit")
    public ResponseEntity<?> editType(@Valid @RequestBody TypeDTO type, BindingResult br) {
        if (br.hasErrors()) {
            System.out.println("Validation error");
            String errMsg = br.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .findFirst().get().toString();
            ErrorDTO errorDTO = new ErrorDTO(errMsg);
            return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        }
        typeService.setTypeById(type);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("{typeId}")
    public ResponseEntity<TypeDTO> getPartById(@PathVariable("typeId") Long id) {
        return ResponseEntity.ok(typeService.findTypeById(id));
    }

    @DeleteMapping("{typeId}")
    public ResponseEntity<?> deletePartById(@PathVariable("typeId") Long id) {
        typeService.deleteTypeById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TypeDTO>> getTypes() {
        return ResponseEntity.ok(typeService.findAllOrderById());
    }
}

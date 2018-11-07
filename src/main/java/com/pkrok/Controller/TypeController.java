package com.pkrok.Controller;

import com.pkrok.Service.TypeService;
import com.pkrok.Domain.TypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("types")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @PostMapping
    public ResponseEntity<?> addType(@RequestBody TypeDTO type) {
        typeService.addType(type);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TypeDTO>> getTypes() {
        return ResponseEntity.ok(typeService.findAllTypes());
    }
}

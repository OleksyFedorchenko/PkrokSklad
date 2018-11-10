package com.pkrok.Controller;

import com.pkrok.Domain.ErrorDTO;
import com.pkrok.Domain.TypeDTO;
import com.pkrok.Service.TypeService;
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

    @GetMapping
    public ResponseEntity<List<TypeDTO>> getTypes() {
        return ResponseEntity.ok(typeService.findAllTypes());
    }
}

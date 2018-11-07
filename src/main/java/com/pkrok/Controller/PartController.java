package com.pkrok.Controller;

import com.pkrok.Service.PartService;
import com.pkrok.Domain.PartsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parts")
public class PartController {

    private PartService partService;

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }

    @PostMapping
    public ResponseEntity<?> addPart(@RequestBody PartsDTO part) {
        partService.addPart(part);
        return new ResponseEntity<Void>(HttpStatus.CREATED); //201
    }

    @GetMapping
    public ResponseEntity<List<PartsDTO>> getParts() {
        return ResponseEntity.ok(partService.findAllParts());
    }


    @GetMapping("{firmName}/{typeName}")
    public ResponseEntity<List<PartsDTO>> getPartsByPar(@PathVariable("firmName") String firmName, @PathVariable("typeName") String typeName) {
        return ResponseEntity.ok(partService.findByFirmAndType(firmName, typeName));
    }


}

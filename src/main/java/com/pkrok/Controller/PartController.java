package com.pkrok.Controller;

import com.pkrok.Service.PartsService;
import com.pkrok.domain.PartsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("parts")
public class PartController {
    @Autowired
    private PartsService partsService;

    @PostMapping
    public ResponseEntity<?> addPart(@RequestBody PartsDTO part) {
        partsService.addPart(part);
        return new ResponseEntity<Void>(HttpStatus.CREATED); //201
    }

    @GetMapping
    public ResponseEntity<List<PartsDTO>> getParts(){
        List<PartsDTO> parts=partsService.findAllParts();
        return new ResponseEntity<List<PartsDTO>>(parts,HttpStatus.OK);
    }


    @GetMapping("{firmName}/{typeName}")
    public ResponseEntity<List<PartsDTO>> getPartsByPar(@PathVariable("firmName")String firmName,@PathVariable("typeName") String typeName){
        List<PartsDTO> parts =partsService.findByFirmAndType(firmName,typeName);
        return new ResponseEntity<List<PartsDTO>>(parts,HttpStatus.OK);
    }


}

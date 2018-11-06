package com.pkrok.Controller;

import com.pkrok.Service.FirmService;
import com.pkrok.domain.FirmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("firms")
public class FirmController {
    @Autowired
    private FirmService firmService;

    @PostMapping
    public ResponseEntity<?> addFirm(@RequestBody FirmDTO firm) {
        firmService.addFirm(firm);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FirmDTO>> getFirms() {
        List<FirmDTO> firms = firmService.findAllFirms();
        return new ResponseEntity<List<FirmDTO>>(firms, HttpStatus.OK);
    }
}

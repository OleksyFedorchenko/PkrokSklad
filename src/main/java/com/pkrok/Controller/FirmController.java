package com.pkrok.Controller;

import com.pkrok.Domain.ErrorDTO;
import com.pkrok.Service.FirmService;
import com.pkrok.Domain.FirmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("firms")
public class FirmController {
    private FirmService firmService;

    @Autowired
    public FirmController(FirmService firmService) {
        this.firmService = firmService;
    }

    @PostMapping
    public ResponseEntity<?> addFirm(@Valid @RequestBody FirmDTO firm, BindingResult br) {
        if (br.hasErrors()) {
            System.out.println("Validation error");
            String errMsg = br.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .findFirst().get().toString();
            ErrorDTO errorDTO = new ErrorDTO(errMsg);
            return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        }
        firmService.addFirm(firm);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping("edit")
    public ResponseEntity<?> editFirm(@Valid @RequestBody FirmDTO firm, BindingResult br) {
        if (br.hasErrors()) {
            System.out.println("Validation error");
            String errMsg = br.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .findFirst().get().toString();
            ErrorDTO errorDTO = new ErrorDTO(errMsg);
            return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        }
        firmService.setFirmById(firm);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("{firmId}")
    public ResponseEntity<FirmDTO> getPartById(@PathVariable("firmId") Long id) {
        return ResponseEntity.ok(firmService.findFirmById(id));
    }

    @DeleteMapping("{firmId}")
    public ResponseEntity<?> deletePartById(@PathVariable("firmId") Long id) {
        firmService.deleteFirmById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FirmDTO>> getFirms() {
        return ResponseEntity.ok(firmService.findAllOrderById());
    }
}

package com.adoreaosenhor.adore_ao_senhor.controller;

import com.adoreaosenhor.adore_ao_senhor.dto.instituicao.CreateInstituitionDTO;
import com.adoreaosenhor.adore_ao_senhor.services.InstitutionProvisioningService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instituicao")
public class InstitutionController {

    @Autowired
    private InstitutionProvisioningService institutionProvisioningService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid CreateInstituitionDTO dados) {
        institutionProvisioningService.provisionNewInstitution(dados);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

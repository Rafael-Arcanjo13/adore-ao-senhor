package com.adoreaosenhor.adore_ao_senhor.controller;

import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.CriarInstituicaoDTO;
import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.TenantProvisioningService;
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
public class TenantController {

    @Autowired
    private TenantProvisioningService tenantProvisioningService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid CriarInstituicaoDTO dados) {
        tenantProvisioningService.provisionarNovoTenant(dados);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

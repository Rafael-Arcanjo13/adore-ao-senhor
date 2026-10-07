package com.adoreaosenhor.adore_ao_senhor.services;

import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.Instituicao;
import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.TenantRepository;
import com.adoreaosenhor.adore_ao_senhor.dto.instituicao.CreateInstituitionDTO;
import com.adoreaosenhor.adore_ao_senhor.dto.usuario.RegisterUserDTO;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UserRole;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstitutionProvisioningService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void provisionarNovoTenant(CreateInstituitionDTO dados) {
        Instituicao instituicao = new Instituicao(dados.nome());
        tenantRepository.save(instituicao);

        RegisterUserDTO registerUserDTO = new RegisterUserDTO(
                dados.emailAdmin(),
                dados.senhaAdmin(),
                UserRole.ADMIN,
                dados.nomeAdmin(),
                dados.telefoneAdmin(),
                dados.cargo()

        );
        userService.cadastrar(registerUserDTO, instituicao);

    }

}

package com.adoreaosenhor.adore_ao_senhor.domain.instituicao;

import com.adoreaosenhor.adore_ao_senhor.domain.usuario.RegisterDTO;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UserRole;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UsuarioRepository;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantProvisioningService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void provisionarNovoTenant(CriarInstituicaoDTO dados) {
        Instituicao instituicao = new Instituicao(dados.nome());
        tenantRepository.save(instituicao);

        RegisterDTO registerDTO = new RegisterDTO(
                dados.emailAdmin(),
                dados.senhaAdmin(),
                UserRole.ADMIN,
                dados.nomeAdmin(),
                dados.telefoneAdmin(),
                dados.cargo()

        );
        usuarioService.cadastrar(registerDTO, instituicao);

    }

    //CORRIGIR ORDEM DE CRIAÇÃO.
}

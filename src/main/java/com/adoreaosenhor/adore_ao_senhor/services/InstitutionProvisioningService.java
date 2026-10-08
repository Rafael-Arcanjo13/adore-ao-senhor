package com.adoreaosenhor.adore_ao_senhor.services;

import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.Institution;
import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.InstitutionRepository;
import com.adoreaosenhor.adore_ao_senhor.dto.instituicao.CreateInstituitionDTO;
import com.adoreaosenhor.adore_ao_senhor.dto.usuario.RegisterUserDTO;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UserRole;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstitutionProvisioningService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void provisionNewInstitution(CreateInstituitionDTO dados) {
        Institution institution = new Institution(dados.name());
        institutionRepository.save(institution);

        RegisterUserDTO registerUserDTO = new RegisterUserDTO(
                dados.emailAdmin(),
                dados.passwordAdmin(),
                UserRole.ADMIN,
                dados.nameAdmin(),
                dados.telephoneAdmin(),
                dados.position()

        );
        userService.cadastrar(registerUserDTO, institution);

    }

}

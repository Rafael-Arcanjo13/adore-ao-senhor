package com.adoreaosenhor.adore_ao_senhor.services;

import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.Institution;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.Position;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.User;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UserRepository;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UserRole;
import com.adoreaosenhor.adore_ao_senhor.dto.instituicao.CreateInstituitionDTO;
import com.adoreaosenhor.adore_ao_senhor.dto.usuario.RegisterUserDTO;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;



import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserServiceTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    private User createUser(RegisterUserDTO dados, Institution institution) {
        var encryptedPassword = passwordEncoder.encode(dados.password());
        User newUser = new User(
                dados.email(),
                encryptedPassword,
                dados.role(),
                dados.name(),
                dados.telephone(),
                dados.position()
        );
        newUser.setInstituicao(institution);
        this.entityManager.persist(newUser);
        return newUser;
    }

    private Institution createInstitution(CreateInstituitionDTO dados) {
        Institution institution = new Institution(dados.name());
        this.entityManager.persist(institution);

        RegisterUserDTO registerUserDTO = new RegisterUserDTO(
                dados.emailAdmin(),
                dados.passwordAdmin(),
                UserRole.ADMIN,
                dados.nameAdmin(),
                dados.telephoneAdmin(),
                dados.position()

        );
        createUser(registerUserDTO, institution);
        return institution;
    }

    @Test
    void LoginWithEmailAndPasswordIncorrect() {
            CreateInstituitionDTO newInstituition =
                    new CreateInstituitionDTO(
                            "Exemplo",
                            "admin@gmail.com",
                            "admin123",
                            "admin",
                            "9999999999",
                            Position.LIDER
                    );
            this.createInstitution(newInstituition);

            assertThrows(
                    AuthenticationException.class,
                    () -> authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    newInstituition.emailAdmin(),
                                    "senhaErrada"
                            )
                    )
            );
    }

    @Test
    void LoginWithEmailAndPasswordCorrect() {
        CreateInstituitionDTO newInstituition =
                new CreateInstituitionDTO(
                        "Exemplo",
                        "admin@gmail.com",
                        "admin123",
                        "admin",
                        "9999999999",
                        Position.LIDER
                );
        this.createInstitution(newInstituition);

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                newInstituition.emailAdmin(),
                                newInstituition.passwordAdmin()
                        )
                );

        assertTrue(authentication.isAuthenticated());


    }

}
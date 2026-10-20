package com.adoreaosenhor.adore_ao_senhor.domain.usuario;

import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.Institution;
import com.adoreaosenhor.adore_ao_senhor.dto.instituicao.CreateInstituitionDTO;
import com.adoreaosenhor.adore_ao_senhor.dto.usuario.RegisterUserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @TestConfiguration
    static class TestConfig {

        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

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
    void findUserByEmail() {
        String emailAdmin = "admin@gmail.com";
        CreateInstituitionDTO newInstituition = new CreateInstituitionDTO("Exemplo", emailAdmin, "admin123", "admin", "9999999999", Position.LIDER);
        this.createInstitution(newInstituition);

        Optional<UserDetails> result = Optional.ofNullable(userRepository.findByEmail(emailAdmin));

        assertThat(result.isPresent());
        assertThat(result.get().getUsername()).isEqualTo("admin@gmail.com");

    }

    @Test
    void findByEmailShouldReturnUserWithCorrectPassword() {
        CreateInstituitionDTO newInstituition = new CreateInstituitionDTO("Exemplo", "admin@gmail.com", "admin123", "admin", "9999999999", Position.LIDER);
        this.createInstitution(newInstituition);

        entityManager.flush();
        entityManager.clear();

        Optional<UserDetails> user = Optional.ofNullable(userRepository.findByEmail("admin@gmail.com"));

        assertTrue(user.isPresent());
        assertTrue(
                passwordEncoder.matches(
                        "admin123",
                        user.get().getPassword()
                )
        );
    }

}
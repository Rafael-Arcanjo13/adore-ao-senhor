package com.adoreaosenhor.adore_ao_senhor.services;

import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.Institution;
import com.adoreaosenhor.adore_ao_senhor.dto.usuario.AuthenticationDTO;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.User;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UserRepository;
import com.adoreaosenhor.adore_ao_senhor.dto.usuario.RegisterUserDTO;
import com.adoreaosenhor.adore_ao_senhor.infra.exceptions.EmailAlreadyRegisteredException;
import com.adoreaosenhor.adore_ao_senhor.infra.security.TokenService;
import com.adoreaosenhor.adore_ao_senhor.util.RandomString;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    public User cadastrar(RegisterUserDTO dados, Institution institution) throws MessagingException, UnsupportedEncodingException {

        if(this.repository.findByEmail(dados.email()) != null) {
            throw new EmailAlreadyRegisteredException();
        }

        var encryptedPassword = passwordEncoder.encode(dados.password());
        String randomCode = RandomString.generateRandomString(64);
        User user = new User(
                dados.email(),
                encryptedPassword,
                dados.role(),
                dados.name(),
                dados.telephone(),
                dados.position(),
                randomCode
        );
        user.setInstituicao(institution);

        mailService.sendVerificationEmail(user);

        this.repository.save(user);

        return user;
    }

    public User entrar(AuthenticationDTO dados) {
        System.out.println("ANTES DO AUTHENTICATE");
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.email(), dados.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        System.out.println("Depois do authenticate");

        User user = (User) auth.getPrincipal();

        return user;
    }

    public boolean verify(String verificationCode) {

        User user = repository.findByVerificationCode(verificationCode);

        if(user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnable(true);
            repository.save(user);

            return true;
        }

    }

}

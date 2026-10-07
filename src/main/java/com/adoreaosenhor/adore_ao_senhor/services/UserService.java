package com.adoreaosenhor.adore_ao_senhor.services;

import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.Instituicao;
import com.adoreaosenhor.adore_ao_senhor.dto.usuario.AuthenticationDTO;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.Usuario;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UsuarioRepository;
import com.adoreaosenhor.adore_ao_senhor.dto.usuario.RegisterUserDTO;
import com.adoreaosenhor.adore_ao_senhor.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrar(RegisterUserDTO dados, Instituicao instituicao) {

        if(this.repository.findByEmail(dados.email()) != null) {
            throw new IllegalArgumentException("E-mail já cadastrado!");
        }

        var encryptedPassword = passwordEncoder.encode(dados.senha());
        Usuario usuario = new Usuario(
                dados.email(),
                encryptedPassword,
                dados.role(),
                dados.nome(),
                dados.telefone(),
                dados.cargo()
        );
        usuario.setInstituicao(instituicao);
        this.repository.save(usuario);

        return usuario;
    }

    public Usuario entrar(AuthenticationDTO dados) {
        System.out.println("ANTES DO AUTHENTICATE");
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        System.out.println("Depois do authenticate");

        Usuario usuario = (Usuario) auth.getPrincipal();

        return usuario;
    }

}

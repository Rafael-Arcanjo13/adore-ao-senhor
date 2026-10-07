package com.adoreaosenhor.adore_ao_senhor.controller;

import com.adoreaosenhor.adore_ao_senhor.domain.usuario.*;
import com.adoreaosenhor.adore_ao_senhor.dto.usuario.AuthenticationDTO;
import com.adoreaosenhor.adore_ao_senhor.dto.usuario.LoginResponseDTO;
import com.adoreaosenhor.adore_ao_senhor.dto.usuario.RegisterUserDTO;
import com.adoreaosenhor.adore_ao_senhor.infra.security.TokenService;
import com.adoreaosenhor.adore_ao_senhor.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/entrar")
    public ResponseEntity entrar(@RequestBody @Valid AuthenticationDTO dados) {
        var usuario = userService.entrar(dados);
        var token = tokenService.generateToken(usuario);

        return ResponseEntity.ok(new LoginResponseDTO(token, usuario.getNome()));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody @Valid RegisterUserDTO dados, Authentication authentication) {

        var usuarioAutenticado = (Usuario) authentication.getPrincipal();
        userService.cadastrar(dados, usuarioAutenticado.getInstituicao());

        return ResponseEntity.ok().build();
    }
}

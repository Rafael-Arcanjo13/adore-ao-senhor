package com.adoreaosenhor.adore_ao_senhor.services;

import com.adoreaosenhor.adore_ao_senhor.domain.usuario.Usuario;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("ENTROU NO LOAD USER BY USERNAME: " + username);
        Usuario usuario = (Usuario) repository.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        System.out.println("USUARIO ENCONTRADO: " + usuario.getEmail());

        return usuario;
    }
}

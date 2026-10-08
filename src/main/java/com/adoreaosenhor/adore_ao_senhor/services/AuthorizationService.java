package com.adoreaosenhor.adore_ao_senhor.services;

import com.adoreaosenhor.adore_ao_senhor.domain.usuario.User;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("ENTROU NO LOAD USER BY USERNAME: " + username);
        User user = (User) repository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        System.out.println("USUARIO ENCONTRADO: " + user.getEmail());

        return user;
    }
}

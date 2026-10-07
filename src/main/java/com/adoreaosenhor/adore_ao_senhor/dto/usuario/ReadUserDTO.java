package com.adoreaosenhor.adore_ao_senhor.dto.usuario;

import com.adoreaosenhor.adore_ao_senhor.domain.usuario.Cargo;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.Usuario;

public record ReadUserDTO(Long id, String nome, String email, String telefone, Cargo cargo) {
    public ReadUserDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getCargo());
    }
}

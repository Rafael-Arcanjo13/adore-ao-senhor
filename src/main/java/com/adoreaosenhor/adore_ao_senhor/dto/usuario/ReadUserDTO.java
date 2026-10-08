package com.adoreaosenhor.adore_ao_senhor.dto.usuario;

import com.adoreaosenhor.adore_ao_senhor.domain.usuario.Position;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.User;

public record ReadUserDTO(Long id, String name, String email, String telephone, Position position) {
    public ReadUserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getTelephone(), user.getPosition());
    }
}

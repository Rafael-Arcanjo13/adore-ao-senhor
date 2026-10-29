package com.adoreaosenhor.adore_ao_senhor.dto.usuario;

import com.adoreaosenhor.adore_ao_senhor.domain.usuario.Position;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.User;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(
        String name,
        Position position,
        String telephone) {
    public UpdateUserDTO(User user) {
        this(user.getName(), user.getPosition(), user.getTelephone());
    }
}

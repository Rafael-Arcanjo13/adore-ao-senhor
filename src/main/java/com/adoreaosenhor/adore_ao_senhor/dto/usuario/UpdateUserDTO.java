package com.adoreaosenhor.adore_ao_senhor.dto.usuario;

import com.adoreaosenhor.adore_ao_senhor.domain.usuario.Position;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(
        @NotNull
        Long id,
        String name,
        Position position,
        String telephone) {
}

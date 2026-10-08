package com.adoreaosenhor.adore_ao_senhor.dto.usuario;

import com.adoreaosenhor.adore_ao_senhor.domain.usuario.Position;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserDTO(
        @NotBlank
        @Email
        String email,

        @NotNull
        String password,

        @NotNull
        UserRole role,

        @NotBlank
        String name,

        @NotBlank
        String telephone,

        @NotNull
        Position position) {
}

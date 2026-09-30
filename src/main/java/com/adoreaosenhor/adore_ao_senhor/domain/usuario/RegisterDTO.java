package com.adoreaosenhor.adore_ao_senhor.domain.usuario;

import com.adoreaosenhor.adore_ao_senhor.domain.instituicao.Instituicao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(String email, String senha, UserRole role, @NotBlank String nome, @NotBlank String telefone, @NotNull Cargo cargo) {
}

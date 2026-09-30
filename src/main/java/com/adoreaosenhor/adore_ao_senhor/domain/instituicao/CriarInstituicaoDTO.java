package com.adoreaosenhor.adore_ao_senhor.domain.instituicao;

import com.adoreaosenhor.adore_ao_senhor.domain.usuario.Cargo;
import com.adoreaosenhor.adore_ao_senhor.domain.usuario.UserRole;
import jakarta.validation.constraints.NotBlank;

public record CriarInstituicaoDTO(String nome, String emailAdmin, String senhaAdmin, @NotBlank String nomeAdmin, @NotBlank String telefoneAdmin, Cargo cargo) {
}

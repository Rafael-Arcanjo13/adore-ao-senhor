package com.adoreaosenhor.adore_ao_senhor.dto.instituicao;

import com.adoreaosenhor.adore_ao_senhor.domain.usuario.Cargo;
import jakarta.validation.constraints.NotBlank;

public record CreateInstituitionDTO(String nome, String emailAdmin, String senhaAdmin, @NotBlank String nomeAdmin, @NotBlank String telefoneAdmin, Cargo cargo) {
}

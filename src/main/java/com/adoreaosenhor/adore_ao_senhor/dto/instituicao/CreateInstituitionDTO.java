package com.adoreaosenhor.adore_ao_senhor.dto.instituicao;

import com.adoreaosenhor.adore_ao_senhor.domain.usuario.Position;
import jakarta.validation.constraints.NotBlank;

public record CreateInstituitionDTO(String name, String emailAdmin, String passwordAdmin, @NotBlank String nameAdmin, @NotBlank String telephoneAdmin, Position position) {
}

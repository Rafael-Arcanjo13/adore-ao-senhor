package com.adoreaosenhor.adore_ao_senhor.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(
        @NotNull
        Long id,
        String nome,
        Cargo cargo,
        String telefone) {
}

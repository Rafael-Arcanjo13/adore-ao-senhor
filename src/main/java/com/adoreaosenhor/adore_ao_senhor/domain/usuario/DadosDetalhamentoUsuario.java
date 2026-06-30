package com.adoreaosenhor.adore_ao_senhor.domain.usuario;

import java.time.LocalDateTime;

public record DadosDetalhamentoUsuario(Long id, String nome, String email, String telefone, Cargo cargo, LocalDateTime dadosCadastro) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getCargo(), usuario.getDataCadastro());
    }
}

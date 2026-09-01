package com.adoreaosenhor.adore_ao_senhor.domain.instituicao;

import com.adoreaosenhor.adore_ao_senhor.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "instituicoes")
@Entity(name = "instituicao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instituicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_instituicao", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "instituicao")
    private List<Usuario> usuarios = new ArrayList<>();

}

package com.adoreaosenhor.adore_ao_senhor.domain.usuario;

public enum Cargo {

    PASTOR("Pastor"),
    PASTORA("Pastora"),
    LIDER("Líder"),
    VOCALISTA("Vocalista"),
    TECLADISTA("Tecladista"),
    VIOLONISTA("Violonista"),
    GUITARRISTA("Guitarrista"),
    BAIXISTA("Baixista"),
    BATERISTA("Baterista");

    private String cargo;

    Cargo(String cargo){
        this.cargo = cargo;
    }

    public String getCargo(){
        return cargo;
    }

}

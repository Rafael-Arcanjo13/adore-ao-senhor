package com.adoreaosenhor.adore_ao_senhor.domain.usuario;

public enum Position {

    PASTOR("Pastor"),
    PASTORA("Pastora"),
    LIDER("Líder"),
    VOCALISTA("Vocalista"),
    TECLADISTA("Tecladista"),
    VIOLONISTA("Violonista"),
    GUITARRISTA("Guitarrista"),
    BAIXISTA("Baixista"),
    BATERISTA("Baterista");

    private String position;

    Position(String position){
        this.position = position;
    }

    public String getPosition(){
        return position;
    }

}

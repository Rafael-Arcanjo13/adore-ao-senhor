package com.adoreaosenhor.adore_ao_senhor.infra.exceptions;

public class InstitutionMemberNotFoundException extends RuntimeException {
    public InstitutionMemberNotFoundException(String message) {
        super(message);
    }

    public InstitutionMemberNotFoundException() {
        super("Integrante não existe na sua instituição!");
    }
}

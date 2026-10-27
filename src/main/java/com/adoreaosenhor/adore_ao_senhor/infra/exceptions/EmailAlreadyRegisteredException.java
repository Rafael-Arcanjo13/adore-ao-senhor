package com.adoreaosenhor.adore_ao_senhor.infra.exceptions;

public class EmailAlreadyRegisteredException extends IllegalArgumentException {

    public EmailAlreadyRegisteredException() { super("E-mail já cadastrado!");}

    public EmailAlreadyRegisteredException(String message) {super(message);}
}

package com.adoreaosenhor.adore_ao_senhor.infra.exceptions;

public class NonExistentEmailException extends RuntimeException {
    public NonExistentEmailException(String message) {
        super(message);
    }

    public NonExistentEmailException() {
        super("E-mail inexistente!");
    }
}

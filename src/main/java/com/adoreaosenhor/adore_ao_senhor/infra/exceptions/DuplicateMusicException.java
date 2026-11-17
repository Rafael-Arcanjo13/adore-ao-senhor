package com.adoreaosenhor.adore_ao_senhor.infra.exceptions;

public class DuplicateMusicException extends RuntimeException {
    public DuplicateMusicException(String message) {
        super(message);
    }

    public DuplicateMusicException() {
        super("E-mail inexistente!");
    }
}

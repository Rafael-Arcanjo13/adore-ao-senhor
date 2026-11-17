package com.adoreaosenhor.adore_ao_senhor.infra.exceptions;

public class NonExistentMusicException extends RuntimeException {
    public NonExistentMusicException(String message) {
        super(message);
    }

    public NonExistentMusicException() {
        super("E-mail inexistente!");
    }
}

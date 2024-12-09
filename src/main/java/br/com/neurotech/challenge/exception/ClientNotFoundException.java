package br.com.neurotech.challenge.exception;

/**
 * Exceção lançada quando um cliente não é encontrado.
 */
public class ClientNotFoundException extends IllegalArgumentException {
    private static final String MESSAGE = "Cliente não encontrado.";

    public ClientNotFoundException() {
        super(MESSAGE);
    }
}

package br.com.neurotech.challenge.exception;

import br.com.neurotech.challenge.entity.util.Constants;

/**
 * Exceção lançada quando um cliente não é encontrado.
 */
public class ClientNotFoundException extends IllegalArgumentException {

    public ClientNotFoundException() {
        super(Constants.ErrorMessages.CLIENT_NOT_FOUND);
    }
}

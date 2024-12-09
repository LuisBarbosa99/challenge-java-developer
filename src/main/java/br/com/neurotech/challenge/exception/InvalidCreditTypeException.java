package br.com.neurotech.challenge.exception;

import static br.com.neurotech.challenge.entity.util.Constants.ErrorMessages.CREDIT_TYPE_UNAVAILABLE;

/**
 * Exceção para tipo de crédito não disponível.
 */
public class InvalidCreditTypeException extends RuntimeException {
    public InvalidCreditTypeException() {
        super(CREDIT_TYPE_UNAVAILABLE);
    }
}

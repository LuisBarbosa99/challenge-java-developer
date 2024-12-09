package br.com.neurotech.challenge.entity.util;

/**
 * Classe de constantes
 */
public class Constants {

    /**
     * Códigos de erro
     */
    public static final class ErrorCodes {
        public static final int CLIENT_NOT_FOUND = 1001;
    }

    /**
     * Mensagens de erro
     */
    public static final class ErrorMessages {
        public static final String CLIENT_NOT_FOUND = "Cliente não encontrado.";
    }

    private Constants() {
        throw new IllegalStateException("Classe de constantes");
    }
}

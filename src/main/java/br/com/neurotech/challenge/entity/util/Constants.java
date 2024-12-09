package br.com.neurotech.challenge.entity.util;

/**
 * Classe de constantes
 */
public class Constants {

    /**
     * Códigos de erro
     */
    public static final class ErrorCodes {
        public static final int FORM_VALIDATION = 1000;
        public static final int CLIENT_NOT_FOUND = 1001;
        public static final int VEHICLE_MODEL_NOT_FOUND = 1002;
    }

    /**
     * Mensagens de erro
     */
    public static final class ErrorMessages {
        public static final String FORM_VALIDATION = "Erro de validação de formulário.";
        public static final String CLIENT_NOT_FOUND = "Cliente não encontrado.";
        public static final String VEHICLE_MODEL_NOT_FOUND = "Modelo de veículo inválido.";
    }

    private Constants() {
        throw new IllegalStateException("Classe de constantes");
    }
}

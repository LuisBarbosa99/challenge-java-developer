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
        public static final int CREDIT_TYPE_UNAVAILABLE = 1003;
    }

    /**
     * Mensagens de erro
     */
    public static final class ErrorMessages {
        public static final String FORM_VALIDATION = "Erro de validação de formulário.";
        public static final String CLIENT_NOT_FOUND = "Cliente não encontrado.";
        public static final String VEHICLE_MODEL_NOT_FOUND = "Modelo de veículo inválido.";
        public static final String CREDIT_TYPE_UNAVAILABLE = "Não há tipo de crédito disponível para este cliente.";
    }

    private Constants() {
        throw new IllegalStateException("Classe de constantes");
    }
}

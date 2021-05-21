package excecoes;

public class MinimoSalarioBaseException extends Exception {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public MinimoSalarioBaseException(String mensagemErro) {
        super(ANSI_YELLOW + "ALERTA: " + ANSI_RESET + mensagemErro);
    }
}

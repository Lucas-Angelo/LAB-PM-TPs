package excecoes;

public class FuncionarioContratadoException extends Exception {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public FuncionarioContratadoException(String mensagemErro) {
        super(ANSI_RED + "ALERTA: " + ANSI_RESET + mensagemErro);
    }
}

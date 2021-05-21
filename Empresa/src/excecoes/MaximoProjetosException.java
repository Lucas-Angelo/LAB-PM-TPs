package excecoes;
public class MaximoProjetosException extends Exception {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public MaximoProjetosException(String mensagemErro) {
        super(ANSI_RED + "ERRO: " + ANSI_RESET + mensagemErro);
    }
}

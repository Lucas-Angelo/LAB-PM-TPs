package excecoes;

/** Classe de excessão customizada.
 * Não solicitada no trabalhado.
 * Utilizado para alertar quando se tenta contratar um funcionário já contratado.
* @author Lucas Ângelo.
* @version 1.0
* @since Release 01, (Revisão para a prova).
*/
public class FuncionarioContratadoException extends Exception {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public FuncionarioContratadoException(String mensagemErro) {
        super(ANSI_RED + "ALERTA: " + ANSI_RESET + mensagemErro);
    }
}

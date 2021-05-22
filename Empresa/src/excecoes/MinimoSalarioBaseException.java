package excecoes;

/** Classe de excessão customizada.
 * Não solicitada no trabalhado.
 * Utilizada para quando tentar definir um salário base menor do que o mínimo salário base de Funcionário.
* @author Lucas Ângelo.
* @version 1.0
* @since Release 01, (Revisão para a prova).
*/
public class MinimoSalarioBaseException extends Exception {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public MinimoSalarioBaseException(String mensagemErro) {
        super(ANSI_YELLOW + "ALERTA: " + ANSI_RESET + mensagemErro);
    }
}

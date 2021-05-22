package excecoes;

/** Classe de excessão customizada.
 * Solicitada no trabalhado.
 * Utilizada quando tenta atualizar a quantidade de projetos de um Gerente com um valor não válido.
* @author Lucas Ângelo.
* @version 1.0
* @since Release 01, (Revisão para a prova).
*/
public class MaximoProjetosException extends Exception {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public MaximoProjetosException(String mensagemErro) {
        super(ANSI_RED + "ERRO: " + ANSI_RESET + mensagemErro);
    }
}

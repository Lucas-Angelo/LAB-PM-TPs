import java.io.IOException;
import java.util.List;

import modelos.Funcionario;
import modelos.Sistema;
/** Classe principal do App, onde está contida a função main. Além disso, alguns métodos de auxílio.
* @author Lucas Ângelo.
* @version 1.0
* @since Release "Revisão para a prova 1 de laboratório de programação modular".
*/
public class App {
    /** Função principal/main do App.
     * @param args Não utilizado.
     * @throws Exception Caso ocorra algum excessão em todo o programa.
     */
    public static void main(String[] args) throws Exception {
        limparTerminal();
        Sistema sistema = new Sistema();
        List<Funcionario> funcionarios = Funcionario.carregarFuncionariosDoArquivo();
        sistema.contratarVarios(funcionarios);

        System.out.print(sistema.toString());
        System.out.println(funcionarios.get(0).calcPagamento());
        System.out.println(sistema.calcTotalAPagar());
    }

    /** Método para limpar o terminal do programa.
    * Eficiente para editores de texto como VSCode, IDE's não precisam.
    * @author Lucas Ângelo
    * @throws InterruptedException Se ocorrer algum erro de interrupção por parte do SO ao tentar lançar o comando no cmd.
    * @throws IOException Se ocorrer algum erro ao tentar emtrar ou sair com o comando no cmd.
    */
    private static void limparTerminal() {
        final String os = System.getProperty("os.name");
        try{
            if (os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (InterruptedException exception) {
            System.out.println("Erro de interrupção ao tentar limpar terminal " + exception);
        } catch (IOException exception) {
            System.out.println("Erro de entrada/saída ao tentar limpar terminal " + exception);
        }
    }
}

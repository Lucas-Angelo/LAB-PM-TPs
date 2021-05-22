import java.io.*;
import java.util.*;

import enums.Nivel;
import modelos.*;

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
        animacaoCarregamento();
        List<Funcionario> funcionariosArquivo = Funcionario.lerFuncionariosDoArquivo("funcionarios.txt");
        
        Sistema sistema = null;
        Funcionario funcionario = null;
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        do {
            limparTerminal();
            opcao = menu(scanner);
            switch (opcao) {
                case 1:
                    limparTerminal();
                    sistema = new Sistema();
                    sistema.contratarVarios(funcionariosArquivo);
                    System.out.println("Sistema criado e funcionários carregados do funcionarios.txt.");
                    pausa(scanner);
                    break;
                case 2:
                    if(sistema!=null) {
                        limparTerminal();
                        funcionario = preencherDadosFuncionario(scanner, sistema, funcionario);
                        sistema.contratar(funcionario);
                        System.out.println("Funcionário criado e contratado.");
                        pausa(scanner);
                    } else {
                        System.out.println("Sistema ainda não criado!");
                        pausa(scanner);
                    }
                    break;
                case 3:
                    if(sistema!=null) {
                        limparTerminal();
                        System.out.println(sistema.toString());
                        pausa(scanner);
                    } else {
                        System.out.println("Sistema ainda não criado!");
                        pausa(scanner);
                    }
                    break;
                case 4:
                    if(sistema!=null) {
                        limparTerminal();
                        int matricula;
                        System.out.print("Informe a matrícula do funcionário: ");
                        matricula = scanner.nextInt();
                        int qtHorasExtra;
                        System.out.print("Informe a quantidade de horas extras a mais: ");
                        qtHorasExtra = scanner.nextInt();
                        sistema.addHorasExtra(matricula, qtHorasExtra);
                        scanner.nextLine();
                        pausa(scanner);
                    } else {
                        System.out.println("Sistema ainda não criado!");
                        pausa(scanner);
                    }
                    break;
                case 5:
                    if(sistema!=null) {
                        limparTerminal();
                        int matricula;
                        System.out.print("Informe a matrícula do funcionário: ");
                        matricula = scanner.nextInt();
                        System.out.println("Valor do salário total do funcionário é: R$" + sistema.calcPagamento(matricula));
                        scanner.nextLine();
                        pausa(scanner);
                    } else {
                        System.out.println("Sistema ainda não criado!");
                        pausa(scanner);
                    }
                    break;
                case 6:
                    if(sistema!=null) {
                        limparTerminal();
                        int matricula;
                        System.out.print("Informe a matrícula do funcionário: ");
                        matricula = scanner.nextInt();
                        System.out.println("Funcionário pago em: R$" + sistema.pagarFuncionario(matricula));
                        scanner.nextLine();
                        pausa(scanner);
                    } else {
                        System.out.println("Sistema ainda não criado!");
                        pausa(scanner);
                    }
                    break;
                case 7:
                    if(sistema!=null) {
                        limparTerminal();
                        System.out.println("O total a ser pago para todos os funcionário desse sistema é: R$" + sistema.calcTotalAPagar());
                        pausa(scanner);
                    } else {
                        System.out.println("Sistema ainda não criado!");
                        pausa(scanner);
                    }
                    break;
                case 8:
                        if(sistema!=null) {
                            limparTerminal();
                            System.out.println("Todos os funcionário desse sistema foram pagos ao todo com: R$" + sistema.pagarTodosOsFuncionarios());
                            pausa(scanner);
                        } else {
                            System.out.println("Sistema ainda não criado!");
                            pausa(scanner);
                        }
                        break;
                case 9:
                    limparTerminal();
                    double salarioBaseNovo;
                    int tipo;
                    System.out.println("1 - Para analista");
                    System.out.println("2 - Para gerente");
                    System.out.print("Informe o tipo do funcionário que se deseja alterar o salário base: ");
                    tipo = scanner.nextInt();
                    System.out.print("Informe o novo salário base: ");
                    salarioBaseNovo = scanner.nextDouble();
                    switch (tipo) {
                        case 1:
                            Analista.setSalarioBase(salarioBaseNovo);
                            break;
                        case 2:
                            Gerente.setSalarioBase(salarioBaseNovo);
                            break;
                    }
                    System.out.println("Salário base alterado.");
                    pausa(scanner);
                    scanner.nextLine();
                    break;
                case 0:
                    if(sistema!=null) {
                        limparTerminal();
                        int salvar;
                        System.out.println("1 - Salvar");
                        System.out.println("2 - Não salvar");
                        System.out.print("Deseja salvar os dados do sistema no arquivo: ");
                        salvar = scanner.nextInt();
                        if(salvar==1) {
                            sistema.salvar("funcionarios.txt");
                            System.out.println("Dados salvos!");
                        }
                    }
                    break;
            }
        } while (opcao!=0);

    }

    private static Funcionario preencherDadosFuncionario(Scanner scanner, Sistema sistema, Funcionario funcionario) {
        String nome;
        System.out.print("Informe o nome do funcionário novo: ");
        nome = scanner.nextLine();
        limparTerminal();
        int tipo;
        System.out.println("1 - Para analista");
        System.out.println("2 - Para gerente");
        System.out.print("Informe o tipo do funcionário novo: ");
        tipo = scanner.nextInt();
        limparTerminal();
        switch (tipo) {
            case 1:
                Analista novoAnalista = null;
                limparTerminal();
                int horasExtra;
                System.out.print("Informe a quantidade de horas extras: ");
                horasExtra = scanner.nextInt();
                limparTerminal();
                int nivel;
                System.out.println("1 - Para analista júnior");
                System.out.println("2 - Para analista sênior");
                System.out.print("Informe o nível do analista: ");
                nivel = scanner.nextInt();
                limparTerminal();
                switch (nivel) {
                    case 1:
                        novoAnalista = new Analista(nome, Nivel.JUNIOR);
                        novoAnalista.setQtHorasExtra(horasExtra);
                        funcionario = novoAnalista;
                        break;
                    case 2:
                        novoAnalista = new Analista(nome, Nivel.JUNIOR);
                        novoAnalista.setQtHorasExtra(horasExtra);
                        funcionario = novoAnalista;
                        break;
                }
                break;
            case 2:
                Gerente novoGerente = null;
                limparTerminal();
                int qtProjetos;
                System.out.print("Informe a quantidade de projetos do novo gerente: ");
                qtProjetos = scanner.nextInt();
                limparTerminal();
                novoGerente = new Gerente(nome, qtProjetos);
                funcionario = novoGerente;
                break;
        }
        scanner.nextLine();
        return funcionario;
    }

    private static int menu(Scanner scanner) {
        int opcao;
        System.out.println("XULAMBS INFORMÁTICA by Lucas Ângelo");
        System.out.println("==========================");
        System.out.println("1 - Criar sistema");
        System.out.println("2 - Contratar novo funcionário");
        System.out.println("3 - Detalhes do sistema");
        System.out.println("4 - Adicionar horas extra");
        System.out.println("5 - Calcular pagamento de um funcionário");
        System.out.println("6 - Pagar um funcionário");
        System.out.println("7 - Calcular pagamento de todos os funcionários");
        System.out.println("8 - Pagar todos os funcionários");
        System.out.println("9 - Definir salário base para tipo de funcionário");
        System.out.println("0 - Sair");
        System.out.print("\nDigite sua opção: ");
        opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }

    static void pausa(Scanner scanner) {
        System.out.println("Enter para continuar.");
        scanner.nextLine();
    }

    private static void animacaoCarregamento() throws InterruptedException {
        int i = 0;
        System.out.println("Carregando banco de dados (funcionários.txt).");
        while(i < 21) {
            System.out.print("[");
            for (int j=0;j<i;j++) {
                System.out.print("#");
            }
            for (int j=0;j<20-i;j++) {
                System.out.print(" ");
            }
            System.out.print("] "+  i*5 + "%");
            if(i<20) {
                System.out.print("\r");
                Thread.sleep(50);
            }
            i++;
        }
        limparTerminal();
        System.out.println("Carregado com sucesso!");
        Thread.sleep(300);
        limparTerminal();
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

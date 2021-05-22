import java.io.*;
import java.util.*;

import enums.Nivel;
import modelos.*;

/** Classe principal do App, onde está contida a função main. Além disso, alguns métodos de auxílio.
* @author Lucas Ângelo.
* @version 1.0
* @since Release 01, (Revisão para a prova).
*/
public class App {
    /** Método principal/main do App.
    * @author Lucas Ângelo.
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
                        funcionario = preencherDadosFuncionario(scanner, funcionario);
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
                        System.out.print("Informe a matrícula do gerente: ");
                        matricula = scanner.nextInt();
                        int qtProjetos;
                        System.out.print("Informe a quantidade de projetos a mais: ");
                        qtProjetos = scanner.nextInt();
                        Gerente gerente = null;
                        try {
                            gerente = (Gerente) sistema.contratado(matricula);
                            gerente.setQtProjetos(gerente.getQtProjetos() + qtProjetos);
                        } catch (Exception erro) {
                            System.out.println("Funcionário informado não é gerente!");
                        }
                        System.out.println("Projetos adicionados");
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
                        System.out.print("Informe a matrícula do analista: ");
                        matricula = scanner.nextInt();
                        int qtHorasExtra;
                        System.out.print("Informe a quantidade de horas extras a mais: ");
                        qtHorasExtra = scanner.nextInt();
                        Analista analista = null;
                        try {
                            analista = (Analista) sistema.contratado(matricula);
                            analista.setQtHorasExtra(analista.getQtHorasExtra() + qtHorasExtra);
                        } catch (Exception erro) {
                            System.out.println("Funcionário informado não é analista!");
                        }
                        System.out.println("Projetos horas extras adicionadas");
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
                        double valor = sistema.calcPagamento(matricula);
                        if(valor!=0)
                            System.out.println("Valor do salário total do funcionário é: R$" + valor);
                        else
                            System.out.println("Funcionário não existe.");
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
                        int matricula;
                        System.out.print("Informe a matrícula do funcionário: ");
                        matricula = scanner.nextInt();
                        double valor = sistema.pagarFuncionario(matricula);
                        if(valor!=0)
                            System.out.println("Funcionário pago em: R$" + valor);
                        else
                            System.out.println("Funcionário não existe.");
                        scanner.nextLine();
                        pausa(scanner);
                    } else {
                        System.out.println("Sistema ainda não criado!");
                        pausa(scanner);
                    }
                    break;
                case 8:
                    if(sistema!=null) {
                        limparTerminal();
                        double valor = sistema.calcTotalAPagar();
                        if(valor!=0)
                            System.out.println("O total a ser pago para todos os funcionário desse sistema é: R$" + valor);
                        else
                            System.out.println("O sistema não possui funcionários.");
                        pausa(scanner);
                    } else {
                        System.out.println("Sistema ainda não criado!");
                        pausa(scanner);
                    }
                    break;
                case 9:
                        if(sistema!=null) {
                            limparTerminal();
                            double valor = sistema.pagarTodosOsFuncionarios();
                            if(valor!=0)
                                System.out.println("Todos os funcionário desse sistema foram pagos ao todo com: R$" + valor);
                            else
                                System.out.println("O sistema não possui funcionários.");
                            pausa(scanner);
                        } else {
                            System.out.println("Sistema ainda não criado!");
                            pausa(scanner);
                        }
                        break;
                case 10:
                    limparTerminal();
                    double salarioBaseNovo;
                    int tipo;
                    System.out.println("1 - Para analista");
                    System.out.println("2 - Para gerente");
                    System.out.print("Informe o tipo do funcionário que se deseja alterar o salário base: ");
                    tipo = scanner.nextInt();
                    System.out.print("Informe o novo salário base: ");
                    salarioBaseNovo = scanner.nextDouble();
                    if (tipo==1)
                        Analista.setSalarioBase(salarioBaseNovo);
                    else if(tipo==2)
                        Gerente.setSalarioBase(salarioBaseNovo);
                    System.out.println("Salário base alterado.");
                    pausa(scanner);
                    scanner.nextLine();
                    break;
                case 11:
                    if(sistema!=null) {
                        limparTerminal();
                        int matricula;
                        System.out.print("Informe a matrícula do analista: ");
                        matricula = scanner.nextInt();
                        int meses;
                        System.out.print("Informe a quantidade de meses trabalhados: ");
                        meses = scanner.nextInt();
                        Analista analista = null;
                        double valor;
                        try {
                            analista = (Analista) sistema.contratado(matricula);
                            valor = analista.valorFerias(meses);
                        } catch (Exception erro) {
                            System.out.println("Funcionário informado não é analista!");
                            valor = 0;
                        }
                        if(valor!=0)
                            System.out.println("Valor das férias: R$" + valor);
                        scanner.nextLine();
                        pausa(scanner);
                    } else {
                        System.out.println("Sistema ainda não criado!");
                        pausa(scanner);
                    }
                    break;
                case 12:
                    if(sistema!=null) {
                        limparTerminal();
                        int matricula;
                        System.out.print("Informe a matrícula do analista: ");
                        matricula = scanner.nextInt();
                        Analista analista = null;
                        double valor;
                        try {
                            analista = (Analista) sistema.contratado(matricula);
                            valor = analista.impostoAPagar();
                        } catch (Exception erro) {
                            System.out.println("Funcionário informado não é analista!");
                            valor = 0;
                        }
                        if(valor!=0)
                            System.out.println("Imposto: R$" + valor);
                        scanner.nextLine();
                        pausa(scanner);
                    } else {
                        System.out.println("Sistema ainda não criado!");
                        pausa(scanner);
                    }
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

    /** Método para solicitar dados de preenchimento para criar um novo objeto da classe Funcionário.
    * Utilizado pelo 'case' == 2 do menu de opções.
    * @author Lucas Ângelo.
    * @param  scanner Scanner - O escaner de dados do teclado para preencher informações do novo funcionário.
    * @param  funcionario Funcionario - O objeto funcionário que será instânciado como algum tipo de funcionário e preenchido.
    * @return Funcionario - Funcionário criado, pronto para ser contratado.
    */
    private static Funcionario preencherDadosFuncionario(Scanner scanner, Funcionario funcionario) {
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

    /** O menu de opções de ações que podem ser efetuados no App.
    * @author Lucas Ângelo.
    * @param  scanner Scanner - O escaner de dados do teclado para escanear a opção escolhida.
    * @return int - Opção numérica escolhida.
    */
    private static int menu(Scanner scanner) {
        int opcao;
        System.out.println("XULAMBS INFORMÁTICA by Lucas Ângelo");
        System.out.println("==========================");
        System.out.println("1 - Criar sistema");
        System.out.println("2 - Contratar novo funcionário");
        System.out.println("3 - Detalhes do sistema");
        System.out.println("4 - Adicionar projetos para gerente");
        System.out.println("5 - Adicionar horas extra para analista");
        System.out.println("6 - Calcular pagamento de um funcionário");
        System.out.println("7 - Pagar um funcionário");
        System.out.println("8 - Calcular pagamento de todos os funcionários");
        System.out.println("9 - Pagar todos os funcionários");
        System.out.println("10 - Definir salário base para tipo de funcionário");
        System.out.println("11 - Calcular valor das férias de um analista");
        System.out.println("12 - Calcular imposto de um analista");
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

    /** Uma função fictícia para emular um carregamento do banco de dados do sistema e deixar o App bonitinho.
    * @author Lucas Ângelo
    * @throws InterruptedException Se o sistema negar a Thread.
    */
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
    * @author Lucas Ângelo.
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

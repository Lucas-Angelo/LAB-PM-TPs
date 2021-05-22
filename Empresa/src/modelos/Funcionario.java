package modelos;

import java.util.LinkedList;
import java.util.regex.PatternSyntaxException;

import arquivos.ArquivoTextoLeitura;

import enums.Nivel;

/** Classe mãe abstrata de Funcionario, contendo atributos e métodos genéricos para todos (Gerente e Analista) os tipos de funcionários.
* @author Lucas Ângelo.
* @version 1.0
* @since Release 01, (Revisão para a prova).
*/
public abstract class Funcionario {
    protected int matricula;
    protected String nome;
    protected double pagtoAReceber;

    private static int matriculaAtual;

    public static final double SALARIO_MIN_BASE;

    static {
        matriculaAtual=0;
        SALARIO_MIN_BASE = 2500.00;
    }
    
    /** Método init para inicializar as ações comuns de todos os contrutores do Funcionario.
     * Ação comum é que todos os objetos de Funcionario é que devem ter uma uma matricula e nome.
    * @author Lucas Ângelo.
    */
    private void init(String nome) {
        this.matricula = matriculaAtual;
        matriculaAtual++;
        this.nome = nome;
    }

    // CONSTRUTORES
    /** Construtor que irá instância um funcionário e necessita de um nome.
     * Inicializa seus dados chamando a função init() da própria classe Fucnionario.
    * @author Lucas Ângelo.
    * @param nome String - Nome do funcionário.
    */
    public Funcionario(String nome) {
        init(nome);
    }

    // GETTERS AND SETTERS
    /** Retorna a matrícula de um funcionário
    * @author Lucas Ângelo.
    * @return int - A matrícula.
    */
    public int getMatricula() {
        return this.matricula;
    }
    
    /** Atualiza o nome de um funcionário
    * @author Lucas Ângelo.
    * @param nome String - O novo nome.
    */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /** Retorna o nome de um funcionário
    * @author Lucas Ângelo.
    * @return String - O nome.
    */
    public String getNome() {
        return this.nome;
    }

    // MÉTODOS
    /** Método abstrato para calculo do salário de um funcionário instânciado.
    * @author Lucas Ângelo.
    * @return double - Valor do salário.
    */
    public abstract double calcPagamento();
    
    /** Método abstrato para pagar um funcionário instânciado.
    * @author Lucas Ângelo.
    * @return double - Valor do salário.
    */
    public abstract double pagar();
    
    /** Função utilizada para ler os dados de funcionários de um arquivo/banco de dados.
     * IMPORTANTE: caso seja criado um sistema e adicionado funcionárioa antes de ler os dados do arquivo, poderá haver conflito de matrículas/funcionários.
    * @author Lucas Ângelo.
    * @param  nomeDoArquivo String - O nome do arquivo de dados de funcionários. Nesse programa está sendo utilizado na main o arquivo "funcionários.txt".
    * @return LinkedList<Funcionario> - Uma lista encadeada com todos os funcionários encontrados no arquivo.
    * @throws PatternSyntaxException Caso a base de dados não esteja bem formatada, com 4 dados divididos por ";". Exemplo: de linha: TipoDoFuncionario;Matrícula;NomeDoFuncionário;QuantidadeDeProjetos
    * @throws NumberFormatException Caso algum dados que deveria ser numérico (Ex: "1"), possua caractere no lugar (Ex: "a1").
    */
    public static LinkedList<Funcionario> lerFuncionariosDoArquivo(String nomeDoArquivo) {
        LinkedList<Funcionario> funcionarios = new LinkedList<>();

        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();
        leitura.abrirArquivo(nomeDoArquivo);

        String linha = null;
        do {
            linha = leitura.ler();
            if(linha!=null) {
                String dados[] = null;
                try {
                    dados = linha.split(";");
                } catch (PatternSyntaxException erro) {
                    erro.printStackTrace();
                }

                int matricula = 0, dadoExtra = 0;
                String nome = new String();

                if(dados!=null && dados.length==4) {
                    nome = new String(dados[2]);
                    try {
                        matricula = Integer.parseInt(dados[1]);
                        dadoExtra = Integer.parseInt(dados[3]);
                    } catch (NumberFormatException erro) {
                        erro.printStackTrace();
                    }
                }

                matriculaAtual = matricula;

                Funcionario funcionario;
                if(dados[0].equals("Gerente"))
                    funcionario = new Gerente(nome, dadoExtra);
                else if(dados[0].equals("AnalistaJR"))
                    funcionario = new Analista(nome, Nivel.JUNIOR, dadoExtra);
                else
                    funcionario = new Analista(nome, Nivel.SENIOR, dadoExtra);
                
                funcionarios.add(funcionario);  
            }
        } while (linha!=null);
        
        return funcionarios;
    }

    /** Função para verificar se dois funcionários são iguais, duas instâncias. 
     * Foi utilizado como diferenciado a matricula.
    * @author Lucas Ângelo.
    * @param  funcionario Funcionario - O funcionário que será verificado se é igual.
    * @return booelan - True caso forem iguais.
    */
    public boolean equals(Funcionario funcionario) {
        boolean igual = false;
        if(this.matricula == funcionario.getMatricula()) {
            igual = true;
        }
        return igual;
    } 
    
    /** Método abstrato para capturar os dados do Funcionario para imprimir/gerar relatório.
     * Informar no padrão do trabalho: TipoDoFuncionario;Matrícula;NomeDoFuncionário;QuantidadeDeProjetos
    * @author Lucas Ângelo.
    * @return String - O relatório do funcionario que pode ser impresso, salvo, etc...
    */
    public abstract String toString();
}
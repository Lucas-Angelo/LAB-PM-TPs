package modelos;

import java.util.LinkedList;

import arquivos.ArquivoTextoLeitura;

/** Classe mãe abstrata de Funcionario, contendo atributos e métodos genéricos para todos os tipos de funcionários.
* @author Lucas Ângelo
* @version 1.0
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

    private void init(String nome) {
        this.matricula = matriculaAtual;
        matriculaAtual++;
        this.nome = nome;
    }

    public Funcionario(String nome) {
        init(nome);
    }
    
    public static LinkedList<Funcionario> carregarFuncionariosDoArquivo() {
        LinkedList<Funcionario> funcionarios = new LinkedList<>();

        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();
        leitura.abrirArquivo("funcionarios.txt");

        String linha = null;
        do {
            linha = leitura.ler();
            if(linha!=null) {
                String dados[] = linha.split(";");

                if(dados[0].equals("Gerente")) {
                    int matricula = 0, projetosQt = 0;
                    String nome = new String(dados[2]);
                    try {
                        matricula = Integer.parseInt(dados[1]);
                        projetosQt = Integer.parseInt(dados[3]);
                    } catch (NumberFormatException erro) {
                        erro.printStackTrace();
                    }
                    matriculaAtual = matricula;
                    Gerente gerente = new Gerente(nome, projetosQt);
                    funcionarios.add(gerente);
                }
                
            }
        } while (linha!=null);
        
        return funcionarios;
    }

    // Getters e Setters
    public int getMatricula() {
        return this.matricula;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }

    /**Métod abstrato para calculo do salário de um funcionário instânciado.
    * @author Lucas Ângelo
    * @return Double - Valor do salário.
    */
    public abstract double calcPagamento();

    public boolean equals(Funcionario funcionario) {
        boolean igual = false;
        if(this.matricula == funcionario.getMatricula()) {
            igual = true;
        }
        return igual;
    } 
    public abstract String toString();
}
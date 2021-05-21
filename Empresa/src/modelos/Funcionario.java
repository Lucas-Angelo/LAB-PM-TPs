package modelos;

import java.util.LinkedList;
import java.util.regex.PatternSyntaxException;

import arquivos.ArquivoTextoLeitura;

import enums.Nivel;

/** Classe mãe abstrata de Funcionario, contendo atributos e métodos genéricos para todos (Gerente e Analista) os tipos de funcionários.
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
    public double getPagtoAReceber() {
        return this.pagtoAReceber;
    }

    /** Método abstrato para calculo do salário de um funcionário instânciado.
    * @author Lucas Ângelo
    * @return Double - Valor do salário.
    */
    public abstract double calcPagamento();
    public abstract double pagar();

    public boolean equals(Funcionario funcionario) {
        boolean igual = false;
        if(this.matricula == funcionario.getMatricula()) {
            igual = true;
        }
        return igual;
    } 
    public abstract String toString();
}
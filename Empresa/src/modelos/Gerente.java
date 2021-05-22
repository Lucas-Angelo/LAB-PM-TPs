package modelos;

import excecoes.MaximoProjetosException;
import excecoes.MinimoSalarioBaseException;

import interfaces.IPJ;

/** Classe para objetos do tipo Gerente.
 * Possui especificamente a quantidade de projetos para cada objeto.
 * Estende da classe Funcionário, pois é um.
 * Implementa a interface IPJ.
* @author Lucas Ângelo.
* @version 1.0
* @since Release 01, (Revisão para a prova).
*/
public class Gerente extends Funcionario implements IPJ {
    protected int qtProjetos;

    protected static double salarioBase;

    public static final int MAX_PROJETOS;
    public static final double PORCENTAGEM_BONUS;

    static {
        salarioBase = SALARIO_MIN_BASE;
        MAX_PROJETOS = 5;
        PORCENTAGEM_BONUS = 0.2;
    }

    /** Método init para inicializar as ações comuns de todos os contrutores do Gerente.
     * Ação comum é que todos os objetos de Gerente é que devem ter uma quantidade de projetos, com isso, precisa-se atualizar o valor do pagamento a receber.
    * @author Lucas Ângelo.
    */
    private void init(int qtProjetos) {
        setQtProjetos(qtProjetos);
        calcPagamento();
    }
    
    // CONSTRUTORES
    /** Construtor que irá instância um gerente já recebendo o nome e a quantidade de projetos que irá trabalhar.
     * Este chama a super classe/mãe Funcionário para preencher os valores.
     * Inicializa seus dados chamando a função init() da própria classe Gerente.
    * @author Lucas Ângelo.
    */
    public Gerente(String nome, int qtProjetos) {
        super(nome);
        init(qtProjetos);
    }
    
    /** Construtor que irá instância um gerente recebendo o nome
     * Este chama a super classe/mãe Funcionário para preencher os valores.
     * Inicializa seus dados chamando a função init() da própria classe Gerente.
    * @author Lucas Ângelo.
    */
    public Gerente(String nome) {
        super(nome);
        init(0);
    }

    // GETTERS AND SETTERS
    /** Atualiza a quantidade de projetos que um objeto de Gerente possui.
     * Não aceita valores maior o máximo de projetos (5) e menores que 0.
     * Exemplo: se o gerente possuir 3 projetos e receber setQtProjetos(2), irá para 2 projetos, não para 5.
     * Ou seja, lembrar que enviar a quantidade de projetos que possui atualmente.
    * @author Lucas Ângelo.
    * @param  qtProjetos int - Quantidade de projetos que o gerente vai cuidar.
    * @throws MaximoProjetosException Se o valor de novos projetos for maior que 5 ou menor que 0.
    */
    public void setQtProjetos(int qtProjetos) {
        if(qtProjetos>MAX_PROJETOS || qtProjetos<0) {
            try {
                throw new MaximoProjetosException("O máximo de projetos que um gerente pode ter é " + MAX_PROJETOS + " e mínimo 0. Quantia de projetos não alterada " + this.qtProjetos + ".");
            } catch (MaximoProjetosException erro) {
                System.out.println(erro.getMessage());
                //erro.printStackTrace();
            }
        } else {
            this.qtProjetos = qtProjetos;
            calcPagamento(); // Quando adiciona horas extra, necessário atualizar o valor do pagamento
        }
    }
    
    /** Retorna a quantia de projetos que o gerente possui.
    * @author Lucas Ângelo.
    * @return int - Quantidade de projetos.
    */
    public int getQtProjetos() {
        return this.qtProjetos;
    }
    
    /** Atualiza estaticamente o salário base de todos os objetos do tipo Gerente.
    * @author Lucas Ângelo.
    * @param  salarioBaseNovo double - O novo salário base.
    * @throws MinimoSalarioBaseException Se o valor inserido for menor que o mínimo salário base que um Funcionário pode ter que é de 2500.
    */
    public static void setSalarioBase(double salarioBaseNovo) {
        if(salarioBaseNovo<SALARIO_MIN_BASE) {
            try {
                throw new MinimoSalarioBaseException("O salário base tem valor mínimo de R$" + SALARIO_MIN_BASE + "0. Salário base de gerentes não alterado.");
            } catch (MinimoSalarioBaseException erro) {
                System.out.println(erro.getMessage());
                //erro.printStackTrace();
            }
        } else {
            salarioBase = salarioBaseNovo;
        }
    }
    
    /** Retorna o valor do salário base de Gerentes.
    * @author Lucas Ângelo.
    * @return int - O valor estatico de salário base de Gerentes.
    */
    public static double getSalarioBase() {
        return salarioBase;
    }

    // MÉTODOS
    /** Calcula o bônus que o gerente recebe pela quantidade de projetos que ele gerencia.
     * Foi utilizado juros simples.
    * @author Lucas Ângelo.
    * @return double - O bônus para a quantidade de projetos.
    */
    @Override
    public double valorBonus() {
        double bonus = 0.0;
        for(int i=0; i<this.qtProjetos; i++) {
            bonus += salarioBase * PORCENTAGEM_BONUS;
        }
        return bonus;
    }
    
    /** Calcula a quantia de pagamento/salário do gerente.
     * Utiliza o método valorBonus() para acrescentar no salário o bõnus recebido por cada projeto.
    * @author Lucas Ângelo.
    * @return double - A quantia de pagamento/salário.
    */
    @Override
    public double calcPagamento() {
        this.pagtoAReceber = salarioBase + valorBonus();
        return (this.pagtoAReceber);
    }
    
    /** Utilliza o calcPagamento() do Gerente para calcular o salário a ser pago, e paga
     * Ao pagar, a quantidade de projeto não é alterado, pois o gerente pode continuar trabalhando em todos os projetos.
    * @author Lucas Ângelo.
    * @return double - O salário pago.
    */
    @Override
    public double pagar() {
        this.pagtoAReceber = calcPagamento();
        // calcPagamento(); // Remover comentário como caso exemplo: tenha que calcular algo como hora extra após pagar
        return this.pagtoAReceber;
    }

    /** Método para capturar os dados do gerente para imprimir/gerar relatório.
     * Informar no padrão do trabalho: Gerente;Matrícula;NomeDoFuncionário;QuantidadeDeProjetos
    * @author Lucas Ângelo.
    * @return String - O relatório do gerente que pode ser impresso, salvo, etc...
    */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder (
            "Gerente" + ";" + this.matricula + ";" + this.nome + ";" + this.qtProjetos
        );
        return stringBuilder.toString();
    }
}

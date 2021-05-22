package modelos;

import enums.Nivel;

import excecoes.MaximoHorasExtrasException;
import excecoes.MinimoSalarioBaseException;

import interfaces.ICLT;

/** Classe para objetos do tipo Analista.
 * Possui especificamente a quantidade de projetos para cada objeto.
 * Estende da classe Funcionário, pois é um.
 * Implementa a interface ICLT.
* @author Lucas Ângelo.
* @version 1.0
* @since Release 01, (Revisão para a prova).
*/
public class Analista extends Funcionario implements ICLT {
    protected int qtHorasExtra;
    protected Nivel nivel;

    protected static double salarioBase;

    public static final int MAX_HORAS_EXTRA;

    static {
        salarioBase = SALARIO_MIN_BASE;
        MAX_HORAS_EXTRA = 40;
    }

    /** Método init para inicializar as ações comuns de todos os contrutores do Analista.
     * Ação comum é que todos os objetos de Analista é que devem ter uma quantidade de horas extra, com isso, precisa-se atualizar o valor do pagamento a receber.
    * @author Lucas Ângelo.
    */
    private void init(Nivel nivel, int qtHorasExtra) {
        this.nivel = nivel;
        setQtHorasExtra(qtHorasExtra);
        calcPagamento();
    }

    // CONSTRUTORES
    /** Construtor que irá instância um analista já recebendo o nome, nível e a quantidade de horas extra que irá trabalhar.
     * Este chama a super classe/mãe Funcionário para preencher os valores.
     * Inicializa seus dados chamando a função init() da própria classe Analista.
    * @author Lucas Ângelo.
    */
    public Analista(String nome, Nivel nivel, int qtHorasExtra) {
        super(nome);
        init(nivel, qtHorasExtra);
    }
    
    /** Construtor que irá instância um analista já recebendo o nome e nível.
     * Este chama a super classe/mãe Funcionário para preencher os valores.
     * Inicializa seus dados chamando a função init() da própria classe Analista.
    * @author Lucas Ângelo.
    */
    public Analista(String nome, Nivel nivel) {
        super(nome);
        init(nivel, 0);
    }

    // GETTERS AND SETTERS
    /** Atualiza a quantidade de horas que um objeto de Analista possui.
     * Não aceita valores maior o máximo de horas extra (40) e menores que 0.
     * Exemplo: se o analista possuir 38 horas extra e receber setQtHorasExtra(2), irá para 2 a quantidade de horas extra, não para 40.
     * Ou seja, lembrar que enviar a quantidade de horas extra que possui atualmente.
    * @author Lucas Ângelo.
    * @param  qtHorasExtra int - Quantidade de horas extra que o analista terá.
    * @throws MaximoHorasExtrasException Se o valor de novos horas extra for maior que 40 ou menor que 0.
    */
    public void setQtHorasExtra(int qtHorasExtra) {
        if(qtHorasExtra>MAX_HORAS_EXTRA || qtHorasExtra<0) {
            try {
                throw new MaximoHorasExtrasException("O máximo de horas extras que um analista pode ter é " + MAX_HORAS_EXTRA + " e mínimo 0. Quantia de horas extra não alterada " + this.qtHorasExtra + ".");
            } catch (MaximoHorasExtrasException erro) {
                System.out.println(erro.getMessage());
                //erro.printStackTrace();
            }
        } else {
            this.qtHorasExtra = qtHorasExtra;
            calcPagamento(); // Quando adiciona horas extra, necessário atualizar o valor do pagamento
        }
    }
    
    /** Retorna a quantia de horas extra que o analista possui.
    * @author Lucas Ângelo.
    * @return int - Quantidade de horas extra.
    */
    public int getQtHorasExtra() {
        return this.qtHorasExtra;
    }
    
    /** Atualiza estaticamente o salário base de todos os objetos do tipo Analista.
    * @author Lucas Ângelo.
    * @param  salarioBaseNovo double - O novo salário base.
    * @throws MinimoSalarioBaseException Se o valor inserido for menor que o mínimo salário base que um Funcionário pode ter que é de 2500.
    */
    public static void setSalarioBase(double salarioBaseNovo) {
        if(salarioBaseNovo<SALARIO_MIN_BASE) {
            try {
                throw new MinimoSalarioBaseException("O salário base tem valor mínimo de R$" + SALARIO_MIN_BASE + "0. Salário base de analistas não alterado.");
            } catch (MinimoSalarioBaseException erro) {
                System.out.println(erro.getMessage());
                //erro.printStackTrace();
            }
        } else {
            salarioBase = salarioBaseNovo;
        }
    }
    
    /** Retorna o valor do salário base de Analista.
    * @author Lucas Ângelo.
    * @return int - O valor estatico de salário base de Analista.
    */
    public static double getSalarioBase() {
        return salarioBase;
    }

    // MÉTODOS
    /** Calcular o valor extra que o Analista vai receber pela sua quantidade de horas extras, levando em consideração seu salário base.
    * @author Lucas Ângelo.
    * @return double - Valor extra.
    */
    @Override
    public double valorHorasExtras() {
        return (this.qtHorasExtra/90.0) * salarioBase;
    }
    
    /** Calcula o valor das ferias de um analista, verificando se já trabalhou 12 meses.
     * O cálculo é apenas o salário base + 1/3 do salário base.
     * Não está em uso @Deprecated.
     * Rereferência: "https://velti.com.br/calculo-de-ferias-aprenda-como-fazer-em-5-passos/".
    * @author Lucas Ângelo.
    * @param  meses INT - Quantia de meses trabalhada.
    * @return double - Valor extra.
    */
    // @Deprecated
    @Override
    public double valorFerias(int meses) {
        double valor = 0;
        if(meses>=12)
            valor = salarioBase + (salarioBase/3) + valorHorasExtras();
        return valor;
    }
    
    /** Calcula o imposto de um analista.
     * Depende do salário base.
     * Não está em uso @Deprecated.
     * Rereferência: "https://www.jornalcontabil.com.br/clt-principais-impostos-sobre-a-folha-de-pagamento/".
    * @author Lucas Ângelo.
    * @return double - Valor do imposto.
    */
    // @Deprecated
    @Override
    public double impostoAPagar() {
        double impostoPercentual = 0;

        if(this.pagtoAReceber >= 1903.99 && this.pagtoAReceber<=2826.65)
            impostoPercentual = 7.5;
        else if(this.pagtoAReceber >= 2826.66 && this.pagtoAReceber<=3751.05)
            impostoPercentual = 15;
        else if(this.pagtoAReceber >= 3751.06 && this.pagtoAReceber<=4664.68)
            impostoPercentual = 22.5;
        else
            impostoPercentual = 4664.68;

        impostoPercentual = impostoPercentual/100;
        
        return salarioBase*impostoPercentual;
    }
    
    /** Calcula a quantia de pagamento/salário do analista.
     * Utiliza o método valorHorasExtras() para acrescentar no salário as horas extras trabalhadas.
    * @author Lucas Ângelo.
    * @return double - A quantia de pagamento/salário.
    */
    @Override
    public double calcPagamento() {
        double horasExtra = valorHorasExtras();
        this.pagtoAReceber = (horasExtra + (salarioBase * this.nivel.getSalarioBase()));
        return this.pagtoAReceber;
    }
    
    /** Utilliza o calcPagamento() do Analista para calcular o salário a ser pago, e paga.
     * Ao pagar, a quantidade de horas extra é atualizada para 0.
    * @author Lucas Ângelo.
    * @return double - O salário pago.
    */
    @Override
    public double pagar() {
        double pago = calcPagamento();
        setQtHorasExtra(0); // Analista resetam a hora extra após receberem o salário
        this.pagtoAReceber = calcPagamento(); // Necessário calcular o novo salário com a hora extra removida.
        return pago;
    }

    /** Método para capturar os dados do Analista para imprimir/gerar relatório.
     * Informar no padrão do trabalho: AnalistaJr/Sr;Matrícula;NomeDoFuncionário;QuantidadeDeProjetos
    * @author Lucas Ângelo.
    * @return String - O relatório do Analista que pode ser impresso, salvo, etc...
    */
    @Override
    public String toString() {
        String abreviacao = (this.nivel == Nivel.JUNIOR) ? "JR" : "SR";
        StringBuilder stringBuilder = new StringBuilder (
            "Analista" + abreviacao + ";" + this.matricula + ";" + this.nome + ";" + this.qtHorasExtra
        );
        return stringBuilder.toString();
    }
}

package modelos;

import enums.Nivel;

import excecoes.MaximoHorasExtrasException;
import excecoes.MinimoSalarioBaseException;

import interfaces.ICLT;

public class Analista extends Funcionario implements ICLT {
    protected int qtHorasExtra;
    protected Nivel nivel;

    protected static double salarioBase;

    public static final int MAX_HORAS_EXTRA;

    static {
        salarioBase = SALARIO_MIN_BASE;
        MAX_HORAS_EXTRA = 40;
    }

    private void init(Nivel nivel, int qtHorasExtra) {
        this.nivel = nivel;
        setQtHorasExtra(qtHorasExtra);
        calcPagamento();
    }

    public Analista(String nome, Nivel nivel, int qtHorasExtra) {
        super(nome);
        init(nivel, qtHorasExtra);
    }
    public Analista(String nome, Nivel nivel) {
        super(nome);
        init(nivel, 0);
    }

    // Getters e Setters
    public void setQtHorasExtra(int qtHorasExtra) {
        if(qtHorasExtra>MAX_HORAS_EXTRA || qtHorasExtra<0) {
            try {
                throw new MaximoHorasExtrasException("O máximo de horas extras que um analista pode ter é " + MAX_HORAS_EXTRA + " e mínimo 0. Quantia de horas extra não alterada.");
            } catch (MaximoHorasExtrasException erro) {
                System.out.println(erro.getMessage());
                //erro.printStackTrace();
            }
        } else {
            this.qtHorasExtra = qtHorasExtra;
            calcPagamento(); // Quando adiciona horas extra, necessário atualizar o valor do pagamento
        }
    }
    public int getQtHorasExtra() {
        return this.qtHorasExtra;
    }
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
    public static double getSalarioBase() {
        return salarioBase;
    }

    // Métodos
    @Override
    public double valorHorasExtras() {
        return (this.qtHorasExtra/90.0) * salarioBase;
    }
    @Override
    public double valorFerias(int meses) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public double impostoAPagar() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public double calcPagamento() {
        double horasExtra = valorHorasExtras();
        this.pagtoAReceber = (horasExtra + (salarioBase * this.nivel.getSalarioBase()));
        return this.pagtoAReceber;
    }
    @Override
    public double pagar() {
        double pago = calcPagamento();
        setQtHorasExtra(0); // Analista resetam a hora extra após receberem o salário
        this.pagtoAReceber = calcPagamento(); // Necessário calcular o novo salário com a hora extra removida.
        return pago;
    }

    @Override
    public String toString() {
        String abreviacao = (this.nivel == Nivel.JUNIOR) ? "JR" : "SR";
        StringBuilder stringBuilder = new StringBuilder (
            "Analista" + abreviacao + ";" + this.matricula + ";" + this.nome + ";" + this.qtHorasExtra
        );
        return stringBuilder.toString();
    }

}

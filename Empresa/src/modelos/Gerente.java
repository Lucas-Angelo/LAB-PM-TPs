package modelos;

import excecoes.MaximoProjetosException;
import excecoes.MinimoSalarioBaseException;
import interfaces.IPJ;

public class Gerente extends Funcionario implements IPJ {
    protected int qtProjetos;

    protected static double salarioBase;

    public static final int MAX_PROJETOS;
    public static final double PORCENTAGEM_BONUS;

    static {
        salarioBase = 2500.0;
        MAX_PROJETOS = 5;
        PORCENTAGEM_BONUS = 0.2;
    }

    private void init(int qtProjetos) {
        setQtProjetos(qtProjetos);
        calcPagamento();
    }
    
    // Construtores
    public Gerente(String nome, int qtProjetos) {
        super(nome);
        init(qtProjetos);
    }
    public Gerente(String nome) {
        super(nome);
        init(0);
    }

    // Getters e Setters
    public void setQtProjetos(int qtProjetos) {
        if(qtProjetos>MAX_PROJETOS || qtProjetos<0) {
            try {
                throw new MaximoProjetosException("O máximo de projetos que um gerente pode ter é " + MAX_PROJETOS + " e mínimo 0. Quantia de projetos não alterada.");
            } catch (MaximoProjetosException erro) {
                System.out.println(erro.getMessage());
                //erro.printStackTrace();
            }
        } else {
            this.qtProjetos = qtProjetos;
        }
    }
    public int getQtProjetos() {
        return this.qtProjetos;
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
    public double calcPagamento() {
        this.pagtoAReceber = salarioBase + valorBonus();
        return (this.pagtoAReceber);
    }
    @Override
    public double valorBonus() {
        double bonus = 0.0;
        for(int i=0; i<this.qtProjetos; i++) {
            bonus += salarioBase * PORCENTAGEM_BONUS;
        }
        return bonus;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder (
            "Gerente" + ";" + this.matricula + ";" + this.nome + ";" + this.qtProjetos
        );
        return stringBuilder.toString();
    }
}

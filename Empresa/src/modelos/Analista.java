package modelos;

import interfaces.ICLT;

public class Analista extends Funcionario implements ICLT {

    public Analista(String nome) {
        super(nome);
        //TODO Auto-generated constructor stub
    }

    @Override
    public double valorHorasExtras() {
        // TODO Auto-generated method stub
        return 0;
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
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

}

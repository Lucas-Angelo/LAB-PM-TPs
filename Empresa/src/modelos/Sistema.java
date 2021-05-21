package modelos;

import java.util.LinkedList;
import java.util.List;

import excecoes.FuncionarioContratadoException;

/** Classe de controle do Sistema, onde está contido os métodos para calcular pagamentos de funcionários.
* @author Lucas Ângelo.
* @version 1.0
*/
public class Sistema {
    public List<Funcionario> funcionarios;

    private void init() {
        this.funcionarios = new LinkedList<Funcionario>();
    }
    
    public Sistema() {
        init();
    }

    public void contratar(Funcionario funcionario) {
        if(!contratado(funcionario))
            this.funcionarios.add(funcionario);
        else {
            try {
                throw new FuncionarioContratadoException("O funcionário com número de matrícula " + funcionario.getMatricula() + " já existente!");
            } catch (FuncionarioContratadoException erro) {
                System.out.println(erro.getMessage());
                //erro.printStackTrace();
            }
        }
    }
    public void contratarVarios(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            contratar(funcionario);
        }
    }
    public boolean contratado(Funcionario funcionario) {
        boolean contratado = false;
        for (Funcionario func : this.funcionarios) {
            if(func.equals(funcionario))
                contratado = true;
        }
        return contratado;
    }

    /** Método para calculo do total que o sistema tem que pagar para todos os funcionários.
    * @author Lucas Ângelo.
    * @return Double - Valor total a pagar de salário para todos os funcionários.
    */
    public double calcTotalAPagar() {
        double total = 0;
        for (Funcionario funcionario : this.funcionarios) {
            total += funcionario.calcPagamento();
        }
        return total;
    }

    /** Método para calculo do salário de um funcionário.
    * @author Lucas Ângelo.
    * @param matricula int - Número de matrícula do funcionário que se deseja obter o valor do salário.
    * @return Double - Valor do salário.
    */
    public double calcPagamento(int matricula) {
        double salario = 0;
        for (Funcionario funcionario : this.funcionarios) {
            if(funcionario.getMatricula()==matricula)
                salario = funcionario.calcPagamento();
        }
        return salario;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Funcionario funcionario : this.funcionarios) {
            stringBuilder.append(funcionario.toString() + "\n");
        }
        return stringBuilder.toString();
    }
}
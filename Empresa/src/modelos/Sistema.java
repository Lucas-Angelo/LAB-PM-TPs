package modelos;

import java.util.LinkedList;
import java.util.List;

import arquivos.ArquivoTextoEscrita;
import excecoes.FuncionarioContratadoException;

/** Classe para objetos do tipo Sistema, utilizada no App main para instância o sistema utilizado na aplicação.
 * Onde de contrata os funcionários e efetua algumas ações da empresa com os funcionários por meio dos métodos.
* @author Lucas Ângelo.
* @version 1.0
* @since Release 01, (Revisão para a prova).
*/
public class Sistema {
    public List<Funcionario> funcionarios;

    /** Método init para inicializar as ações comuns de todos os contrutores do Sistema.
     * Ação comum é que todos os objetos de Sistemas devem ter uma lista de funcionários.
    * @author Lucas Ângelo.
    */
    private void init() {
        this.funcionarios = new LinkedList<Funcionario>();
    }
    
    /** Construtor vazio que irá inicializar o objeto instânciado.
    * @author Lucas Ângelo.
    */
    public Sistema() {
        init();
    }

    /** Método utilizado para contratar um funcionário novo.
     * Recebe o objeto do funcionário que irá ser contratado.
     * Objeto recebido é adicionado na lista de funcionários do sistema.
    * @author Lucas Ângelo.
    * @param  funcionario Funcionario - A instância do novo Funcionário que irá ser contratado/adicionado na lista do sistema.
    */
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
    
    /** Método utilizado para contratar vários funcionário novos de uma vez.
     * Recebe uma lista de objetos funcionários que irão ser contratados.
     * Lista recebida é adicionado na lista de funcionários do sistema.
    * @author Lucas Ângelo.
    * @param  funcionarios List<Funcionario> - A lista de funcionários novos que irão ser contratados/adicionados na lista do sistema.
    */
    public void contratarVarios(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            contratar(funcionario);
        }
    }
    
    /** Método que verifica se um funcionário já foi contradado, por meio da instância do funcionário.
    * @author Lucas Ângelo.
    * @param  funcionario Funcionario - O funcionário que será verificado.
    * @return booelan - Se o funcionário já foi contratado retorna true.
    */
    public boolean contratado(Funcionario funcionario) {
        boolean contratado = false;
        for (Funcionario func : this.funcionarios) {
            if(func.equals(funcionario))
                contratado = true;
        }
        return contratado;
    }
    
    /** Método que verifica se um funcionário já foi contradado, por meio da matrícula.
    * @author Lucas Ângelo.
    * @param  matricula int - A matrícula de um funcionário que será verificada.
    * @return Funcionario - Retorna o objeto do funcionário se ele já tiver sido contratado, senão, retorna null.
    */
    public Funcionario contratado(int matricula) {
        Funcionario contratado = null;
        for (Funcionario func : this.funcionarios) {
            if(func.getMatricula()==matricula)
                contratado = func;
        }
        return contratado;
    }

    /** Método para calcular o total que o sistema tem que pagar para todos os funcionários.
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
    
    /** Método para pagar todos os funcionários.
    * @author Lucas Ângelo.
    * @return Double - Valor total pago de salários para todos os funcionários.
    */
    public double pagarTodosOsFuncionarios() {
        double total = 0;
        for (Funcionario funcionario : this.funcionarios) {
            total += funcionario.pagar();
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
    
    /** Método para pagar o salário de um funcionário.
    * @author Lucas Ângelo.
    * @param matricula int - Número de matrícula do funcionário que se deseja pagar o salário.
    * @return Double - Valor do salário pago.
    */
    public double pagarFuncionario(int matricula) {
        double salario = 0;
        for (Funcionario funcionario : this.funcionarios) {
            if(funcionario.getMatricula()==matricula)
                salario = funcionario.pagar();
        }
        return salario;
    }

    /** Método para capturar os dados do sistema para imprimir/gerar relatório.
     * Informar no padrão do trabalho: TipoDoFuncionário;Matrícula;NomeDoFuncionário;QuantidadesExtras
    * @author Lucas Ângelo.
    * @return String - O relatório do sistema que pode ser impresso, salvo, etc...
    */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Funcionario funcionario : this.funcionarios) {
            stringBuilder.append(funcionario.toString() + "\n");
        }
        return stringBuilder.toString();
    }
    
    /** Método para salvar os dados atualizados do sistema em um arquivo informado.
     * Utiliza o relatório gerado pelo toString() do Sistema
    * @author Lucas Ângelo.
    * @param  nomeDoArquivo String - Nome do arquivo onde se deseja salvar o relatório do sistema. Padrão: "funcionarios.txt".
    */
    public void salvar(String nomeDoArquivo) {
        ArquivoTextoEscrita escrita = new ArquivoTextoEscrita();
        escrita.abrirArquivo(nomeDoArquivo);
        escrita.escrever(toString());
        escrita.fecharArquivo();
    }
}

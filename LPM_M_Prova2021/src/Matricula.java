import java.util.LinkedList;
import java.util.List;

public class Matricula {

    protected Disciplina disciplina;
    protected Situacao situacao;
    protected List<Atividade> atividades;

    public static final int SOMA_VALOR_ATIVIDADES;

    static {
        SOMA_VALOR_ATIVIDADES = 100;
    }

    private void init() {
        this.situacao = Situacao.PENDENTE;
        this.atividades = new LinkedList<Atividade>();
        this.situacao = Situacao.CURSANDO;
    }

    public Matricula() {
        init();
    }

    public int notaFinal() {
        return somatorioNotasDasAtividades();
    }

    public void lancarNota(int avaliacao, int nota) {
        try {
            if(somatorioNotasDasAtividades() + nota > SOMA_VALOR_ATIVIDADES)
                throw new Exception("Essa nota ultrapassa o máximo: " + SOMA_VALOR_ATIVIDADES + "!");
            else
                this.atividades.get(avaliacao).lancarNota(nota);
        } catch (IndexOutOfBoundsException erro) {
            System.err.println("Não existe uma avaliação " + avaliacao + " cadastrada!");
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
        }
    }
    
    public void addAtividade(Atividade nova) {
        try {
            if((somatorioValorDasAtividades() + nova.getValor()) > SOMA_VALOR_ATIVIDADES)
                throw new Exception("O somatório de atividades ultrapassou o limite: " + SOMA_VALOR_ATIVIDADES + " pontos!");
            else {
                this.atividades.add(nova);
            }
        } catch (Exception erro) {
            System.err.println(erro);
        }
        
    }

    private int somatorioValorDasAtividades() {
        int valores = 0;
        for (Atividade atividade : this.atividades) {
            valores+=atividade.getValor();
        }
        return valores;
    }

    private int somatorioNotasDasAtividades() {
        int valores = 0;
        for (Atividade atividade : this.atividades) {
            valores+=atividade.getNota();
        }
        return valores;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Disciplina getDisciplina() {
        return this.disciplina;
    }

    public Situacao getSituacao() {
        return this.situacao;
    }

    public void fecharSemestre() {
        try {
            if(somatorioValorDasAtividades()!=SOMA_VALOR_ATIVIDADES)
                throw new Exception("O somatório das notas deve ser " + SOMA_VALOR_ATIVIDADES + " para fechar o semestre!");
            else {
                this.situacao = Situacao.APROVADO;
            }
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }
    }
    
}

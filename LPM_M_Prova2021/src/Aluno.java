import java.util.LinkedList;
import java.util.List;

public class Aluno {

    protected int matricula;
    public String nome;
    protected IAcademico nivel;
    protected List<Matricula> disciplinas;
    protected Historico historico;

    private static int matriculaAtual;

    static {
        matriculaAtual = 0;
    }

    private void init(String nome, String nivel) {
        this.matricula = matriculaAtual;

        this.nome = nome;
        if(nivel.equals("Graduacao"))
            this.nivel = new Graduacao();
        else if(nivel.equals("PosGraduacao"))
            this.nivel = new PosGraduacao();

        this.disciplinas = new LinkedList<Matricula>();
        this.historico = new Historico();

        matriculaAtual++;
    }

    public Aluno(String nome, String nivel) {
        init(nome, nivel);
    }

    public void matricular(Matricula disciplina) {
        this.disciplinas.add(disciplina);
    }

    public boolean aprovado(String nomeDisciplina) {
        boolean res = false;

        Matricula matriculaAprovado = null;
        for (Matricula matricula : this.disciplinas) {
            if(matricula.getDisciplina().getNome()==nomeDisciplina)
                matriculaAprovado = matricula;
        }

        if(this.nivel.aprovado(matriculaAprovado)) {
            res = true;
            historico.addMateria(matriculaAprovado);
        }

        return res;
    }

    public String imprimirHistorico() {
        StringBuilder stringBuilder = new StringBuilder();

        int creditos=0;
        for (Matricula matricula : this.disciplinas) {
            stringBuilder.append(matricula.getDisciplina().getNome() + " " + matricula.notaFinal() + "\n");
            creditos+=this.nivel.creditos(matricula);
        }
        stringBuilder.append("Total de creditos restantes: " + (nivel.oquePrecisaPraPassar()-creditos));

        return stringBuilder.toString();
    }

    public boolean formado() {
        boolean res = false;
        int creditos=0;
        for (Matricula matricula : this.disciplinas) {
            creditos+=this.nivel.creditos(matricula);
        }

        if(creditos>=this.nivel.oquePrecisaPraPassar())
            res = true;

        return res;
    }

}
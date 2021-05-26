import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        limparTerminal();

        Atividade atividade1 = new Atividade(25); // Está tratando as excessões de atividade
        Atividade atividade2 = new Atividade(25);
        Atividade atividade3 = new Atividade(25);
        Atividade atividade4 = new Atividade(25);
        
        Disciplina disciplina = new Disciplina("aed1", 100);

        Matricula matricula = new Matricula();
        matricula.addAtividade(atividade1); // Tratando as excessoes
        matricula.addAtividade(atividade2);
        matricula.addAtividade(atividade3);
        matricula.addAtividade(atividade4);

        matricula.setDisciplina(disciplina);

        for(int i=0; i<4; i++)
            matricula.lancarNota(i, 25); // Tratando as excessoes

        Aluno alunoGraduacao = new Aluno("Lucas", "Graduacao");
        alunoGraduacao.matricular(matricula);
        matricula.fecharSemestre();
        System.out.println("ALUNO DE GRADUAÇÃO EXEMPLO: ");
        System.out.println("Aprovado na disciplina " + disciplina.getNome() + ": " + alunoGraduacao.aprovado("aed1"));
        System.out.println("Histórico:");
            System.out.println(alunoGraduacao.imprimirHistorico());
        System.out.println("Formado: " + alunoGraduacao.formado());


        System.out.println("\n");
        alunoGraduacao = new Aluno("Lucas", "PosGraduacao");
        alunoGraduacao.matricular(matricula);
        matricula.fecharSemestre();
        System.out.println("ALUNO DE PÓS-GRADUAÇÃO EXEMPLO: ");
        System.out.println("Aprovado na disciplina " + disciplina.getNome() + ": " + alunoGraduacao.aprovado("aed1"));
        System.out.println("Histórico:");
            System.out.println(alunoGraduacao.imprimirHistorico());
        System.out.println("Formado: " + alunoGraduacao.formado());


        System.out.println("\n");
        alunoGraduacao = new Aluno("Lucas", "Graduacao");
        alunoGraduacao.matricular(matricula);
        matricula.fecharSemestre();
        System.out.println("ALUNO DE GRADUAÇÃO EXEMPLO (Não estava falando qual o tipo, então usei outro de graduação): ");
        System.out.println("Aprovado na disciplina " + disciplina.getNome() + ": " + alunoGraduacao.aprovado("aed1"));
        System.out.println("Histórico:");
            System.out.println(alunoGraduacao.imprimirHistorico());
        System.out.println("Formado: " + alunoGraduacao.formado());

    }

    private static void limparTerminal() {
        final String os = System.getProperty("os.name");
        try{
            if (os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (InterruptedException exception) {
            System.out.println("Erro de interrupção ao tentar limpar terminal " + exception);
        } catch (IOException exception) {
            System.out.println("Erro de entrada/saída ao tentar limpar terminal " + exception);
        }
    }
}

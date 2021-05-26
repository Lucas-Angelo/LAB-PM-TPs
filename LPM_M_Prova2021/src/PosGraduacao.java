public class PosGraduacao implements IAcademico {

    @Override
    public boolean aprovado(Matricula materia) {
        boolean res= false;

        if(materia.getSituacao()==Situacao.APROVADO)
            res = true;

        return res;
    }

    @Override
    public int creditos(Matricula materia) {
        int horasPorCredrito = 110;
        int horas = materia.getDisciplina().getCargaHoraria();

        int creditosParaMateria = 0;
        if(materia.getSituacao() == Situacao.APROVADO)
            while((horas-horasPorCredrito)>=0) {
                creditosParaMateria++;
                horas = horas-horasPorCredrito;
            }

        return creditosParaMateria;
    }

    @Override
    public int oquePrecisaPraPassar() {
        return 25;
    }
    
}

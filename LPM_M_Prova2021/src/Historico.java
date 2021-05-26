import java.util.HashSet;
import java.util.Set;

public class Historico {
    private Set<Matricula> disciplinas;
    
    public Historico(){
        this.disciplinas = new HashSet<Matricula>();
    }

    public boolean addMateria(Matricula aprovada){
        return this.disciplinas.add(aprovada);
    }

    public Set<Matricula> getDisciplinas() {
        return this.disciplinas;
    }

    
}

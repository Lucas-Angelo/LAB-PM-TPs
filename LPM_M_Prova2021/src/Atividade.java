public class Atividade {
    private static final int MAX_VALOR = 25;
    private int valor;
    private int nota;

    private void init(int valor) {
        setValor(valor);
    }

    public Atividade(int valor) {
        init(valor);
    }
    
    public int getValor() {
        return valor;
    }
    
    public void setValor(int valor) {
        try {
            if(valor>=1 && valor<=MAX_VALOR)
                this.valor = valor;
            else
                throw new Exception("O valor mínimo é 1 e máximo é " + MAX_VALOR);
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
        }
    }

    public int getNota() {
        return nota;
    }
    
    public void lancarNota(int nota) {
        try {
            if(nota>=0 && nota<=this.valor)
                this.nota = nota;
            else
                throw new Exception("Não pode ter notas menores que 0 e maiores que " + this.valor);
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
        }
    }
      
    
}

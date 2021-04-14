public abstract class Comida implements Comparable<Comida> {

    protected static final double VALOR_ADICIONAL = 1.99;

    protected String descricao;
    protected int qtAdicionais;
    protected double precoBase;
    protected double precoFinal;

    protected Comida(String desc, double preco) {
        this.setDescricao(desc);
        this.precoBase = preco;
    }

    protected void setDescricao(String qual) { // Inicia a string com pizza ou sanduiche
        this.descricao = qual + "do Xulambs Delivery ";
    }

    @Override
    public String toString() {
        return this.descricao + ("com " + this.qtAdicionais + " adicionais");
    }

    /**
     * Comidas são iguais se têm a mesma descrição
     */
    @Override
    public boolean equals(Object obj) {
        return (this.toString().equals(obj.toString()));
    }

    /**
     * Compara as comidas pelo seu preço final
     * 
     * @param arg0 A outra comida a ser comparada
     * @return Padrão do Comparable: -1, 0, 1
     */
    @Override
    public int compareTo(Comida arg0) {
        // compareTo:
        // negativo, se eu sou 'menor' que o outro
        // 0 se os objetos são considerados iguais
        // positivo, se eu sou 'maior' que o outro

        if (this.precoTotal() < arg0.precoTotal())
            return -1;
        else if (this.precoTotal() > arg0.precoTotal())
            return 1;
        else
            return 0;
    }

    public int getQtAdicionais() {
        return this.qtAdicionais;
    }

    public boolean addIngrediente() {

        int limite = this.maxAdicionais();

        if (this.qtAdicionais < limite) {
            this.qtAdicionais++;
            return true;
        } else {
            return false;
        }
    }

    public boolean retIngrediente() {

        if (this.qtAdicionais > 0) {
            this.qtAdicionais--;
            return true;
        } else {
            return false;
        }
    }

    public abstract double precoTotal();

    protected abstract int maxAdicionais();

}

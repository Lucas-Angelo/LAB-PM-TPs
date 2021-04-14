package util;

public class ListaEncadeada<T extends Comparable<T>> {

    Elemento<T> primeiro;
    Elemento<T> ultimo;
    Elemento<T> atual;

    public ListaEncadeada() {
        this.primeiro = new Elemento<T>(null);
        this.ultimo = this.atual = this.primeiro;
    }

    public void add(T dado) {
        Elemento<T> novo = new Elemento<T>(dado);

        this.ultimo.proximo = novo;
        this.ultimo = novo;
    }

    public boolean empty() {
        return (this.primeiro == this.ultimo);
    }

    public T getAtual() {
        return this.atual.dado;
    }

    public void next() {
        this.atual = this.atual.proximo;
        if (this.atual == null)
            this.atual = this.primeiro;
    }

    public T search(T mock) { // objeto mock: substituto para um obj real. Tem o que importa para a busca.
        Elemento<T> aux = this.primeiro.proximo;

        while (aux != null) {
            if (aux.dado.equals(mock))
                return aux.dado;
            else
                aux = aux.proximo;
        }
        return null;
    }

    @Override
    public String toString() {
        if (this.empty())
            return null;

        StringBuilder sb = new StringBuilder("LISTA DE ");

        Elemento<T> aux = this.primeiro.proximo;

        while (aux != null) {
            sb.append(aux.dado.toString() + "\n");
            aux = aux.proximo;
        }
        return sb.toString();
    }

    public T greatest() {
        if (this.empty())
            return null;

        Elemento<T> aux = this.primeiro.proximo;
        T theGreatest = aux.dado;

        while (aux != null) {
            if (aux.dado.compareTo(theGreatest) > 0)
                theGreatest = aux.dado;

            aux = aux.proximo;
        }
        return theGreatest;

    }

}

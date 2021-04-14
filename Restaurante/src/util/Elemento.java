package util;

public class Elemento<T> { // T: template
    T dado;
    Elemento<T> proximo;

    public Elemento(T d) {
        this.dado = d;
        this.proximo = null;
    }

}

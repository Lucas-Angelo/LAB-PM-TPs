import util.ListaEncadeada;

public class Cliente {

    public String nome;
    private String CPF;
    // private Pedido[] pedidos;
    private ListaEncadeada<Pedido> pedidos;
    private IFidelidade categoria;
    private int qtPedidos;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.CPF = cpf;
        this.pedidos = new ListaEncadeada<>();
        this.categoria = null;
        this.qtPedidos = 0;
    }

    public void addPedido(Pedido p) {
        pedidos.add(p);
        this.qtPedidos++;
        this.verificarCategoria();
    }

    private void verificarCategoria() {
        IFidelidade teste;

        if (this.categoria == null) { // se não tenho fidelidade, posso passar para a primeira.
            teste = new Cliente10();
        } else { // se já estou na primeira, posso passar para segunda.
            teste = new Cliente25();
        }

        if (teste.desconto(this.pedidos) > 0) // se na categoria nova eu tenho desconto,
            this.categoria = teste; // mudo a minha categoria.
    }

    public double desconto() {
        if (this.categoria == null)
            return 0.0;
        else
            return this.categoria.desconto(this.pedidos);
    }
}

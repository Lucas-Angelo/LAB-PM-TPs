import java.io.Serializable;

import util.Data;
import util.ListaEncadeada;

public class Pedido implements Comparable<Pedido>, Serializable {

    private static final Data HOJE;

    private static double valorTotalVendido;
    private static int ultId;

    // private Comida[] comidas;
    transient private ListaEncadeada<Comida> comidas;
    private int qtComidas;
    private boolean fechado;
    private double precoTotal;
    private int idPedido;
    transient Data dataPedido;

    static {
        ultId = 0;
        valorTotalVendido = 0.0;
        HOJE = new Data(13, 4, 2021);
    }

    public static String valorTotalVendido() {
        return "R$ " + String.format("%.2f", valorTotalVendido);
    }

    public Pedido() {
        this.comidas = new ListaEncadeada<>();
        this.qtComidas = 0;
        this.fechado = false;
        this.precoTotal = 0.0;
    }

    public int quantComidas() {
        return this.qtComidas;
    }

    /**
     * 
     * @return
     */
    public double valorTotal() {
        double valorAux = 0.0;

        comidas.next();
        Comida atual = comidas.getAtual();

        if (this.fechado)
            valorAux = this.precoTotal;
        else {
            while (atual != null) {
                valorAux += atual.precoTotal();
                comidas.next();
                atual = comidas.getAtual();
            }
        }
        return valorAux;
    }

    public boolean fecharPedido() {
        if (this.quantComidas() > 0) {
            if (!this.fechado) {
                this.precoTotal = this.valorTotal();
                valorTotalVendido += this.precoTotal;
                this.idPedido = ++ultId;
                this.fechado = true;
                this.dataPedido = HOJE;
            }
        }
        return this.fechado;
    }

    public boolean addComida(Comida nova) {
        boolean adicionou = false;
        if (!this.fechado) {
            comidas.add(nova);
            this.qtComidas++;
            adicionou = true;
        }
        return adicionou;
    }

    @Override
    public String toString() {
        final String inicio = (this.fechado) ? "Pedido concluído Nº " + this.idPedido : "Pedido em aberto. ";

        StringBuilder builder = new StringBuilder(inicio + "\n");

        builder.append(comidas.toString());

        builder.append("-----------------------------");
        builder.append("Total: R$" + this.valorTotal());

        return builder.toString();

        // for(int i=0; i<this.quantComidas();i++){
        // builder.append(String.format("%02d", (i+1))+" - ");
        // builder.append(comidas[i].toString()+"\n");
        // }
    }

    @Override
    public int compareTo(Pedido arg0) {
        if (!this.fechado)
            return -1;
        else if (!arg0.fechado)
            return 1;
        else {
            if (this.precoTotal < arg0.precoTotal)
                return -1;
            else if (this.precoTotal > arg0.precoTotal)
                return 1;
            else
                return 0;
        }
    }
}

/**
 * Com a evolução do sistema, um pedido pode conter até 10 comidas diferentes. 
 * O relatório de um pedido deve mostrar a descrição e o valor de cada um dos
 * produtos, detalhadamente, e o valor total do pedido.
 */

import util.Data;
import util.ListaEncadeada;

public class Cliente10 implements IFidelidade {

    Data hoje = new Data(24, 4, 2021); // fake, mock, teste

    @Override
    public double desconto(ListaEncadeada<Pedido> pedidos) {
        // double desconto = 0.0;
        // double valorPedidos=0.0;

        // for (Pedido pedido : pedidos) {
        // if(pedido!=null){
        // Data aux = pedido.dataPedido.acrescentaDias(31);
        // if(!hoje.maisRecente(aux)){
        // valorPedidos += pedido.valorTotal();
        // }
        // }
        // }

        // if(valorPedidos>=100.00)
        // desconto = 0.1;

        return 0;
    }

}

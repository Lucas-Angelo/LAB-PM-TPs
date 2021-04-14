import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class App {

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int menu(Scanner teclado) {
        limparTela();
        System.out.println("XULAMBS DELIVERY");
        System.out.println("==========================");
        System.out.println("1 - Novo pedido");
        System.out.println("2 - Incluir comida em pedido");
        System.out.println("3 - Detalhes do pedido");
        System.out.println("4 - Fechar pedido");
        System.out.println("5 - Contabilidade");
        System.out.println("0 - Sair");

        int opcao = teclado.nextInt();
        teclado.nextLine();
        return opcao;
    }

    static void pausa(Scanner teclado) {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

    static Comida criarComida(Scanner teclado) {
        System.out.print("Incluir no pedido(1-Pizza 2-Sanduíche): ");
        int tipo = Integer.parseInt(teclado.nextLine());
        Comida nova;
        switch (tipo) {
        case 1:
            nova = new Pizza(false);
            break;
        case 2:
            nova = new Sanduiche(false);
            break;
        default:
            nova = null;
            break;
        }
        if (nova != null) {
            System.out.print("Quantos adicionais: ");
            int quantos = Integer.parseInt(teclado.nextLine());
            for (int i = 0; i < quantos; i++)
                nova.addIngrediente();
        }
        return nova;
    }

    // static void testou(Pedido p, Method m) throws IllegalAccessException,
    // IllegalArgumentException, InvocationTargetException{
    // m.invoke(p);

    // }

    static void criarNovo(Pedido p) {
        p = new Pedido();
        System.out.print("Novo pedido criado. ");
    }

    public static void main(String[] args) throws Exception {
        FileOutputStream arqPedidos = new FileOutputStream("arqPedidos.bin");
        ObjectOutputStream streamPedidos = new ObjectOutputStream(arqPedidos);

        Scanner teclado = new Scanner(System.in);
        Pedido pedido = null;
        int opcao = -1;

        do {
            opcao = menu(teclado);
            limparTela();
            switch (opcao) {
            case 1:
                pedido = new Pedido();
                System.out.print("Novo pedido criado. ");
                pausa(teclado);
                break;
            case 2:
                if (pedido != null) {
                    Comida aux = criarComida(teclado);
                    if (aux != null) {
                        if (pedido.addComida(aux))
                            System.out.println("Adicionado: " + aux);
                        else
                            System.out.println("Não foi possível adicionar.");
                    } else
                        System.out.print("Inválido. Favor tentar novamente. ");
                } else
                    System.out.print("Pedido ainda não foi aberto. ");
                pausa(teclado);
                break;
            case 3:
                if (pedido != null) {
                    System.out.println(pedido);
                } else
                    System.out.print("Pedido ainda não foi aberto. ");
                pausa(teclado);
                break;
            case 4:
                if (pedido != null) {
                    if (pedido.fecharPedido()) {
                        System.out.println("Pedido fechado. Valor: R$ " + pedido.valorTotal());
                        streamPedidos.writeObject(pedido);
                    } else
                        System.out.println("Pedido não foi fechado. Inclua comidas ou crie novo pedido.");
                } else
                    System.out.print("Pedido ainda não foi aberto. ");
                pausa(teclado);
                break;
            case 5:
                System.out.println("Total vendido: " + Pedido.valorTotalVendido());
                streamPedidos.close();
                FileInputStream arqLeitura = new FileInputStream("arqPedidos.bin");
                ObjectInputStream streamLeitura = new ObjectInputStream(arqLeitura);

                Pedido p = (Pedido) streamLeitura.readObject();

                pausa(teclado);
                break;
            }
        } while (opcao != 0);

        System.out.println("FIM");
        streamPedidos.close();
        teclado.close();
    }
}

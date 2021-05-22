package arquivos;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/** Classe para tratamento de escrita em arquivos.
* @author Eveline.
*/
public class ArquivoTextoEscrita {

    private BufferedWriter saida;

    public void abrirArquivo(String nomeArquivo) {
        try {
            this.saida = new BufferedWriter(new FileWriter(nomeArquivo));
        } catch (FileNotFoundException excecao) {
            System.out.println("Erro: Arquivo não encontrado.");
        } catch (IOException excecao) {
            System.out.println("Erro: I/O de arquivo falhou.");
        }
    }

    public void fecharArquivo() {
        try {
            this.saida.close();
        } catch (IOException excecao) {
            System.out.println("Erro: I/O de arquivo falhou.");
        }
    }

    public void escrever(String textoEntrada) {
        try {
            this.saida.write(textoEntrada);
        } catch (IOException excecao) {
            System.out.println("Erro: I/O de arquivo falhou.");
        }
    }
}
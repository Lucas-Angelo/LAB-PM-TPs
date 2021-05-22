package arquivos;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** Classe para tratamento de escrita em arquivos.
* @author Eveline.
*/
public class ArquivoTextoLeitura {

    private BufferedReader entrada;

    public void abrirArquivo(String nomeArquivo) {
        try {
            this.entrada = new BufferedReader(new FileReader(nomeArquivo));
        } catch (FileNotFoundException excecao) {
            System.out.println("Arquivo não encontrado");
        }
    }

    public void fecharArquivo() {
        try {
            this.entrada.close();
        } catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);
        }
    }

    public String ler() {
        String textoEntrada;

        try {
            textoEntrada = this.entrada.readLine();
        } catch (EOFException excecao) { // Exceção de final de arquivo.
            return null;
        } catch (IOException excecao) {
            System.out.println("Erro de leitura: " + excecao);
            return null;
        }
        return textoEntrada;
    }
}
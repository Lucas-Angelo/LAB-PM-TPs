package util;

public class Data {
    private static final Data MINDATA;
    private static Data maxData;

    // constante: dias de cada mês
    private static final int[] DIASDOMES = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    // atributos
    private int dia, mes, ano;

    static {
        MINDATA = new Data(true);
        maxData = MINDATA;
    }

    /**
     * Construtor privado para iniciar a data mínima
     */
    private Data(boolean minimo) {
        if (minimo) {
            this.dia = this.mes = 1;
            this.ano = 1900;
        } else {
            Data aux = new Data();
            this.dia = aux.dia;
            this.mes = aux.mes;
            this.ano = aux.ano;
        }
    }

    /**
     * @return the dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * @return the ano
     */
    public int getAno() {
        return ano;
    }

    public Data(int d, int m, int a) {
        this.dia = d;
        this.mes = m;
        this.ano = a;
        if (!this.dataValida()) { // se a data não é válida... (método da própria classe)
            this.dia = MINDATA.getDia();
            this.mes = MINDATA.getMes();
            this.ano = MINDATA.getAno();
        }
        if (this.maisRecente(maxData)) {
            maxData.dia = this.dia;
            maxData.mes = this.mes;
            maxData.ano = this.ano;
        }
    }

    public Data() {
        maxData = maxData.acrescentaDias(1);
        this.dia = maxData.dia;
        this.mes = maxData.mes;
        this.ano = maxData.ano;
    }

    /// <summary>
    /// Método que retorna se o ano é bissexto ou não
    /// Para regras do ano bissexto:
    /// http://educacao.uol.com.br/disciplinas/matematica/ano-bissexto-eles-se-repetem-a-cada-4-anos.htm
    /// http://www.sogeografia.com.br/Curiosidades/?pg=4
    /// </summary>
    /// <returns>Booleano, se o ano é bissexto (true) ou não (false)</returns>
    public boolean anoBissexto() {

        if (this.ano % 100 == 0)
            return ((this.ano % 400) == 0); // ano divisível por 400 --> bissexto
        else
            return ((this.ano % 4) == 0);
    }

    /// <summary>
    /// Verifica se uma data é válida
    /// </summary>
    /// <returns>True se é válida // False se não é válida</returns>
    private Boolean dataValida() {
        Boolean resposta = true; // resposta sobre a validade
        if (this.ano < 1900)
            resposta = false;
        else {
            if (this.mes < 1 || this.mes > 12) // mês<1 ou mês>12 --> data inválida
                resposta = false;
            else {
                if (this.anoBissexto()) // senão, caso de 29/02 em ano bissexto --> data válida
                    DIASDOMES[2] = 29;
                if (this.dia > DIASDOMES[this.mes]) // senao, verifica validade de acordo com o mês atual
                    resposta = false;
                DIASDOMES[2] = 28;
            }
        }
        return resposta; // retorna a resposta obtida
    }

    /// <summary>
    /// acrescenta dias à data
    /// </summary>
    /// <param name="quant">quantidade de dias a acrescentar</param>
    public Data acrescentaDias(int quant) {
        Data aux = new Data(this.dia, this.mes, this.ano);

        aux.dia += quant; // acrescenta a quantidade ao dia atual e, em seguida, devemos ajustar mês e ano

        if (aux.anoBissexto())
            DIASDOMES[2] = 29; // se o ano é bissexto, altera fevereiro para 29 dias

        while (aux.dia > DIASDOMES[aux.mes]) { // enquanto os dias ultrapassam o limite de dias do mês atual... ajustar

            aux.dia = aux.dia - DIASDOMES[aux.mes]; // desconta a quantidade de dias do mês
            aux.mes++; // avança o mês

            if (aux.mes > 12) // se passar de 12 meses...
            {
                aux.mes = aux.mes - 12; // desconta-se 1 ano
                aux.ano++; // avança o ano.
                if (aux.anoBissexto())
                    DIASDOMES[2] = 29; // verifica se o novo ano é bissexto para ajustar os dias de fevereiro.
                else
                    DIASDOMES[2] = 28;
            }
        }
        return aux;
    }

    public boolean maisRecente(Data outra) {

        if (this.ano > outra.ano)
            return true;
        else if (this.ano < outra.ano)
            return false;

        if (this.mes > outra.mes)
            return true;
        else if (this.mes < outra.mes)
            return false;

        if (this.dia > outra.dia)
            return true;
        else
            return false;
    }

    /// <summary>
    /// Retorna a data formatada
    /// </summary>
    /// <returns>String com a data no formato dd/mm/aaaa</returns>
    public String dataFormatada() {

        return (String.format("%02d", this.dia) + "/" + String.format("%02d", this.mes) + "/"
                + String.format("%4d", this.ano));
    }

}

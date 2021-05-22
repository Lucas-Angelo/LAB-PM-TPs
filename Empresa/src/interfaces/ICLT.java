package interfaces;

/** Interface para ICLT.
 * Utilizada pela classe Analista.
* @author Lucas Ângelo.
* @version 1.0
* @since Release 01, (Revisão para a prova).
*/
public interface ICLT {
    public double valorHorasExtras();
    public double valorFerias(int meses);
    public double impostoAPagar();
}

package enums;

import interfaces.INivel;

/** Enum customizado.
 * Solicitada no trabalhado.
 * Utilizado para os tipos de analistas.
* @author Lucas Ângelo.
* @version 1.0
* @since Release 01, (Revisão para a prova).
*/
public enum Nivel implements INivel {
    JUNIOR(1.0d),
    SENIOR(1.5d);

    double multiplicador;

    Nivel(double multiplicador){
        this.multiplicador = multiplicador;
    }
    
    @Override
    public double getSalarioBase(){
        return this.multiplicador;
    }
}

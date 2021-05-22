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
    JUNIOR {
        @Override
        public double getSalarioBase() {
            return 1.0;
        }
    },
    SENIOR {
        @Override
        public double getSalarioBase() {
            return 1.5;
        }
    }
}

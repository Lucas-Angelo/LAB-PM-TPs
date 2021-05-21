package enums;

import interfaces.INivel;

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

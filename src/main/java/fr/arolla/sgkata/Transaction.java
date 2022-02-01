package fr.arolla.sgkata;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(BigDecimal amount, Type type, LocalDate date) {
    public enum Type {

        DEPOSIT(BigDecimal.ONE), WITHDRAWAL(BigDecimal.valueOf(-1));

        private final BigDecimal multiplier;

        Type(BigDecimal multiplier) {
            this.multiplier = multiplier;
        }
    }

    public BigDecimal getSignedAmount() {
        return amount.multiply(this.type.multiplier);
    }
}

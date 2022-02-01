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

    public static Transaction of(BigDecimal amount, Transaction.Type type, LocalDate date) {
        if (amount.signum() == -1) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        return new Transaction(amount, type, date);
    }
}

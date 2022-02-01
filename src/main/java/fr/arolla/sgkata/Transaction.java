package fr.arolla.sgkata;

import java.math.BigDecimal;

public record Transaction(BigDecimal amount, Type type) {
    public enum Type {DEPOSIT, WITHDRAWAL}
}

package fr.arolla.sgkata;

import java.math.BigDecimal;

public class Account {


    private final BigDecimal initialDeposit;

    public Account() {
        this.initialDeposit = BigDecimal.ZERO;
    }

    public Account(BigDecimal initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    public BigDecimal getBalance() {
        return this.initialDeposit;
    }
}

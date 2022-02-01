package fr.arolla.sgkata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

    private final List<Transaction> transactions;

    public Account() {
        this(BigDecimal.ZERO);
    }

    public Account(BigDecimal initialDeposit) {
        transactions = new ArrayList<>();
        transactions.add(new Transaction(initialDeposit));
    }

    public BigDecimal getBalance() {
        return this.transactions.stream().map(Transaction::amount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public void depose(BigDecimal deposit) {
        if (deposit.signum() == -1) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        this.transactions.add(new Transaction(deposit));
    }
}

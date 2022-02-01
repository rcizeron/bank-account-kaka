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
        transactions.add(createTransaction(initialDeposit, Transaction.Type.DEPOSIT));
    }

    public BigDecimal getBalance() {
        return this.transactions.stream().map(Transaction::getSignedAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    private Transaction createTransaction(BigDecimal amount, Transaction.Type type) {
        if (amount.signum() == -1) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        return new Transaction(amount, type);
    }

    public void depose(BigDecimal deposit) {

        this.transactions.add(createTransaction(deposit, Transaction.Type.DEPOSIT));
    }

    public void withdraw(BigDecimal withdraw) {
        this.transactions.add(createTransaction(withdraw, Transaction.Type.WITHDRAWAL));
    }
}

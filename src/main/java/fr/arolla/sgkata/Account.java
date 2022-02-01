package fr.arolla.sgkata;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Account {

    private final List<Transaction> transactions;

    public Account() {
        this(BigDecimal.ZERO);
    }

    public Account(BigDecimal initialDeposit) {
        this(initialDeposit, LocalDate.now());
    }

    public Account(BigDecimal initialDeposit, LocalDate date) {
        transactions = new ArrayList<>();
        transactions.add(createTransaction(initialDeposit, Transaction.Type.DEPOSIT, date));
    }

    public BigDecimal getBalance() {
        return this.transactions.stream().map(Transaction::getSignedAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Transaction> getTransactions() {
        return transactions.stream().sorted(Comparator.comparing(Transaction::date)).toList();
    }

    private Transaction createTransaction(BigDecimal amount, Transaction.Type type, LocalDate date) {
        if (amount.signum() == -1) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        return new Transaction(amount, type, date);
    }

    public void depose(BigDecimal deposit, LocalDate date) {
        this.transactions.add(createTransaction(deposit, Transaction.Type.DEPOSIT, date));
    }

    public void withdraw(BigDecimal withdraw, LocalDate date) {
        this.transactions.add(createTransaction(withdraw, Transaction.Type.WITHDRAWAL, date));
    }
}

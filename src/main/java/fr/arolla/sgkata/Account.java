package fr.arolla.sgkata;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Account {

    private final List<Transaction> transactions;

    public Account(BigDecimal initialDeposit, LocalDate date) {
        transactions = new ArrayList<>();
        transactions.add(Transaction.of(initialDeposit, Transaction.Type.DEPOSIT, date));
    }

    public BigDecimal getBalance() {
        return this.transactions.stream().map(Transaction::getSignedAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Transaction> getTransactions() {
        return transactions.stream().sorted(Comparator.comparing(Transaction::date)).toList();
    }

    public void depose(BigDecimal deposit, LocalDate date) {
        this.transactions.add(Transaction.of(deposit, Transaction.Type.DEPOSIT, date));
    }

    public void withdraw(BigDecimal withdraw, LocalDate date) {
        this.transactions.add(Transaction.of(withdraw, Transaction.Type.WITHDRAWAL, date));
    }
}

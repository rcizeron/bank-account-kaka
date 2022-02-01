package fr.arolla.sgkata;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Statement(Transaction.Type operation, LocalDate date, BigDecimal amount, BigDecimal balance) {
    public static Statements getStatements(Account account) {

        return account.getTransactions().stream().collect(
                Statements::new,
                (accumulator, transaction) -> {
                    BigDecimal currentBalance = accumulator.getCurrentBalance();
                    accumulator.add(getStatementFromTransaction(currentBalance, transaction));
                },
                Statements::merge
        );
    }

    private static Statement getStatementFromTransaction(BigDecimal oldBalance, Transaction transaction) {
        return new Statement(transaction.type(), transaction.date(), transaction.amount(), oldBalance.add(transaction.getSignedAmount()));
    }
}

package fr.arolla.sgkata;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record Statement(Transaction.Type operation, LocalDate date, BigDecimal amount, BigDecimal balance) {
    public static List<Statement> getStatements(Account account) {
        List<Statement> statements = new ArrayList<>();
        BigDecimal balance = BigDecimal.ZERO;

        for (Transaction transaction :
                account.getTransactions()) {
            balance = balance.add(transaction.getSignedAmount());
            statements.add(new Statement(transaction.type(), transaction.date(), transaction.amount(), balance));
        }

        return statements;
    }
}

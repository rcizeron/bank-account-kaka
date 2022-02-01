package fr.arolla.sgkata;

import static org.assertj.core.api.Assertions.assertThat;
import static java.time.LocalDate.now;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HistoryTest {

    @Test
    void should_have_one_operation_after_one_deposit() {
        Account account = new Account();

        assertThat(Statement.getStatements(account)).containsExactly(
                new Statement(Transaction.Type.DEPOSIT, now(), BigDecimal.ZERO, BigDecimal.ZERO)
        );
    }

    @Test
    void several_deposits() {
        Account account = new Account(BigDecimal.valueOf(100), LocalDate.of(2022, 2, 1));
        account.depose(BigDecimal.valueOf(150), LocalDate.of(2022, 2, 2));
        account.depose(BigDecimal.valueOf(200), LocalDate.of(2022, 2, 3));

        assertThat(Statement.getStatements(account)).containsExactly(
                new Statement(Transaction.Type.DEPOSIT, LocalDate.of(2022, 2, 1), BigDecimal.valueOf(100), BigDecimal.valueOf(100)),
                new Statement(Transaction.Type.DEPOSIT, LocalDate.of(2022, 2, 2), BigDecimal.valueOf(150), BigDecimal.valueOf(250)),
                new Statement(Transaction.Type.DEPOSIT, LocalDate.of(2022, 2, 3), BigDecimal.valueOf(200), BigDecimal.valueOf(450))
        );

    }
}

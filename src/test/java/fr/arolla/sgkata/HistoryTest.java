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
}

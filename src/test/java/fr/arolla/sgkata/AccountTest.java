package fr.arolla.sgkata;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AccountTest {

    @Test
    void should_make_a_non_empty_initial_deposit() {
        Account account = new Account(BigDecimal.valueOf(100), LocalDate.of(2022, 1, 1));

        assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(100));
    }

    @Test
    void should_have_a_transaction_as_initial_deposit() {
        Account account = new Account(new BigDecimal(100), LocalDate.of(2022, 1, 1));

        assertThat(account.getTransactions()).containsExactly(new Transaction(BigDecimal.valueOf(100), Transaction.Type.DEPOSIT, LocalDate.of(2022, 1, 1)));
    }

    @Test
    void should_have_correct_balance_after_deposit() {
        Account account = new Account(BigDecimal.valueOf(100), LocalDate.of(2022, 1, 1));
        account.depose(BigDecimal.valueOf(150), LocalDate.of(2022, 1, 1));

        BigDecimal expected = BigDecimal.valueOf(100).add(BigDecimal.valueOf(150));

        assertThat(account.getBalance()).isEqualTo(expected);
    }

    @Test
    void should_not_be_able_to_depose_negative_amount() {
        Account account = new Account(BigDecimal.valueOf(100), LocalDate.of(2022, 1, 1));

        assertThatThrownBy(() -> account.depose(BigDecimal.valueOf(-150), LocalDate.of(2022, 1, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Amount must be positive");
    }

    @Test
    void should_have_correct_balance_after_withdrawal() {
        Account account = new Account(BigDecimal.valueOf(100), LocalDate.of(2022, 1, 1));
        account.withdraw(BigDecimal.valueOf(150), LocalDate.of(2022, 1, 1));

        assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(100).add(BigDecimal.valueOf(-150)));

        assertThat(account.getTransactions()).containsExactly(
                new Transaction(BigDecimal.valueOf(100), Transaction.Type.DEPOSIT, LocalDate.of(2022, 1, 1)),
                new Transaction(BigDecimal.valueOf(150), Transaction.Type.WITHDRAWAL, LocalDate.of(2022, 1, 1)));
    }
}

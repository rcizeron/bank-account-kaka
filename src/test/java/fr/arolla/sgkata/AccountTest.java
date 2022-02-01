package fr.arolla.sgkata;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AccountTest {

    @Test
    void should_make_an_empty_initial_deposit() {
        assertThat(new Account().getBalance()).isEqualTo(BigDecimal.ZERO);
    }
}

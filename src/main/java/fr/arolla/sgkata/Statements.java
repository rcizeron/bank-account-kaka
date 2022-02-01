package fr.arolla.sgkata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record Statements(List<Statement> statements) {

    public Statements() {
        this(new ArrayList<>());
    }

    public BigDecimal getCurrentBalance() {
        return statements.isEmpty() ? BigDecimal.ZERO
                : statements.get(statements.size() - 1).balance();
    }

    public void add(Statement statement) {
        statements.add(statement);
    }

    public void merge(Statements others) {
        statements.addAll(others.statements);
    }
}

/*
 * Copyright © 2013-2018 Suning.com Co., Ltd.
 *
 * This file is part of euphoria project.
 * It can not be copied and/or distributed without the express
 * permission of cloudytrace group.
 */
package com.river.engine.ast.expressions.function;


import com.river.engine.ast.Expression;
import com.river.engine.ast.Operand;
import com.river.engine.context.DataContext;
import com.river.engine.grammar.RuleSQLParser;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

/**
 * A in expression for judging whether a operand contains in a collection of operands.
 *
 */
@AllArgsConstructor
public class InExpression implements Expression {
    private static final long serialVersionUID = 4916903919906487382L;

    @NonNull
    private final Operand comparator;

    @NonNull
    private final List<Operand> collections;

    @Override
    public boolean evaluate(DataContext context) {
        Object currentValue = comparator.calculate(context);

        return collections.stream()
            .map(op -> op.calculate(context))
            .anyMatch(value -> Objects.equals(currentValue, value));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            InExpression that = (InExpression) obj;
            if (Objects.equals(that.comparator, comparator)) {
                for (Operand operand : collections) {
                    if (!elementExistInCollections(operand, that.collections)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean elementExistInCollections(Operand operand, List<Operand> operands) {
        for (Operand op : operands) {
            if (Objects.equals(operand, op)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(comparator, RuleSQLParser.IN, collections);
    }

    @Override
    public String toString() {
        String collectStr = collections.stream()
            .map(Operand::toString)
            .collect(joining(", ", "(", ")"));

        return comparator.toString() + " IN " + collectStr;
    }
}

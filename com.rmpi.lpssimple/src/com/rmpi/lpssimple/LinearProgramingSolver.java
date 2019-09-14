package com.rmpi.lpssimple;

import java.math.BigDecimal;
import java.util.*;

import static com.rmpi.lpssimple.OptimizationMode.MAXIMIZE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;

public class LinearProgramingSolver {
    public final List<LinearConstraint> constraints = new ArrayList<>();
    public LinearConstraint target = new LinearConstraint(0, 0, 0);
    public OptimizationMode optimizationMode = MAXIMIZE;

    private BigDecimal argX = ZERO, argY = ZERO, value = ZERO;

    public void solve() {
        List<Map.Entry<BigDecimal, BigDecimal>> vertices = new ArrayList<>();
        int numOfConstraints = constraints.size();
        for (int i = 0; i < numOfConstraints; i++)
            for (int j = i + 1; j < numOfConstraints; j++) {
                LinearConstraint first = constraints.get(i);
                LinearConstraint second = constraints.get(j);
                BigDecimal determinant = first.xCoefficient.multiply(second.yCoefficient)
                        .subtract(first.yCoefficient.multiply(second.xCoefficient));
                if (determinant.equals(ZERO))
                    continue;
                BigDecimal solX = first.yCoefficient.multiply(second.constantTerm)
                        .subtract(second.yCoefficient.multiply(first.constantTerm))
                        .divide(determinant, HALF_EVEN);
                BigDecimal solY = second.xCoefficient.multiply(first.constantTerm)
                        .subtract(first.xCoefficient.multiply(second.constantTerm))
                        .divide(determinant, HALF_EVEN);
                if (constraints.stream().allMatch(constraint -> constraint.satisfies(solX, solY)))
                    vertices.add(new AbstractMap.SimpleImmutableEntry<>(solX, solY));
            }
        Map.Entry<BigDecimal, BigDecimal> argOpt;

        switch (optimizationMode) {
            case MAXIMIZE:
                argOpt = vertices.stream()
                        .max(Comparator.comparing(e -> target.evaluate(e.getKey(), e.getValue())))
                        .orElseGet(() -> new AbstractMap.SimpleImmutableEntry<>(ZERO, ZERO));
                break;
            case MINIMIZE:
                argOpt = vertices.stream()
                        .min(Comparator.comparing(e -> target.evaluate(e.getKey(), e.getValue())))
                        .orElseGet(() -> new AbstractMap.SimpleImmutableEntry<>(ZERO, ZERO));
                break;
            default:
                throw new AssertionError();
        }

        argX = argOpt.getKey();
        argY = argOpt.getValue();
        value = target.evaluate(argX, argY);
    }

    public BigDecimal getArgX() {
        return argX;
    }

    public BigDecimal getArgY() {
        return argY;
    }

    public BigDecimal getValue() {
        return value;
    }
}

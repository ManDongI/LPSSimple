package com.rmpi.lpssimple;

import java.math.BigDecimal;

import static com.rmpi.lpssimple.BigDecimalComparator.GE;

public class LinearConstraint {
    public BigDecimal xCoefficient, yCoefficient, constantTerm;
    public BigDecimalComparator comparator;

    public LinearConstraint(double xCoefficient, double yCoefficient, double constantTerm) {
        this(xCoefficient, yCoefficient, constantTerm, GE);
    }

    public LinearConstraint(BigDecimal xCoefficient, BigDecimal yCoefficient, BigDecimal constantTerm) {
        this(xCoefficient, yCoefficient, constantTerm, GE);
    }

    public LinearConstraint(double xCoefficient, double yCoefficient, double constantTerm, BigDecimalComparator comparator) {
        this(BigDecimal.valueOf(xCoefficient), BigDecimal.valueOf(yCoefficient), BigDecimal.valueOf(constantTerm), comparator);
    }

    public LinearConstraint(BigDecimal xCoefficient, BigDecimal yCoefficient, BigDecimal constantTerm, BigDecimalComparator comparator) {
        this.xCoefficient = xCoefficient;
        this.yCoefficient = yCoefficient;
        this.constantTerm = constantTerm;
        this.comparator = comparator;
    }

    public BigDecimal evaluate(BigDecimal x, BigDecimal y) {
        return xCoefficient.multiply(x)
                .add(yCoefficient.multiply(y))
                .add(constantTerm);
    }

    public boolean satisfies(BigDecimal x, BigDecimal y) {
        return comparator.compare(evaluate(x, y), BigDecimal.ZERO);
    }
}

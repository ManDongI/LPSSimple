package com.rmpi.lpssimple;

import java.math.BigDecimal;
import java.util.function.BiPredicate;

public enum BigDecimalComparator {
    EQ(BigDecimal::equals),
    GT((a, b) -> a.compareTo(b) > 0),
    LT((a, b) -> a.compareTo(b) < 0),
    GE((a, b) -> a.compareTo(b) >= 0),
    LE((a, b) -> a.compareTo(b) <= 0);

    private final BiPredicate<BigDecimal, BigDecimal> comparator;

    BigDecimalComparator(BiPredicate<BigDecimal, BigDecimal> comparator) {
        this.comparator = comparator;
    }

    public boolean compare(BigDecimal a, BigDecimal b) {
        return comparator.test(a, b);
    }
}

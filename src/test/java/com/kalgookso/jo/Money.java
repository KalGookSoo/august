package com.kalgookso.jo;

public class Money {

    public static Money ZERO = new Money(0L);

    private final long value;

    public Money(long value) {
        this.value = value;
    }

    public static Money wons(int value) {
        return new Money(value);
    }

    public long getValue() {
        return value;
    }

    public Money plus(Money amount) {
        return new Money(this.value + amount.value);
    }

    public Money minus(Money amount) {
        return new Money(this.value - amount.value);
    }

    public Money times(double percent) {
        return new Money((long) (this.value * percent));
    }

    public boolean isLessThan(Money other) {
        return value < other.value;
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return value >= other.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Money)) return false;
        Money other = (Money) obj;
        return value == other.value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return String.format("Money{value=%d}", value);
    }

}
package binarymash.cloud.shopper.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {

    private static final Money ZERO = new Money(java.math.BigDecimal.ZERO);
    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isGreaterThanZero() {
        return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThan(Money money) {
        return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
    }

    public Money add(Money money) {
        return new Money(setScale(this.amount.add(money.getAmount())));
    }

    public Money subtract(Money money) {
        return new Money(setScale(this.amount.subtract(money.getAmount())));
    }

    public Money multiply(int multiplier) {
        return new Money(setScale(this.amount.multiply(new BigDecimal(multiplier))));
    }

    public Money addPercentage(int percentage) {
        return new Money(setScale(this.amount.multiply(new BigDecimal(1 + percentage/100))));
    }

    public Money lessPercentage(int percentage) {
        return new Money(setScale(this.amount.multiply(new BigDecimal(1 - percentage/100))));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private BigDecimal setScale(BigDecimal input) {
        return input.setScale(2, RoundingMode.HALF_EVEN);
    }
}

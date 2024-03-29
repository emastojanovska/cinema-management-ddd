package mk.ukim.finki.emt.sharedkernel.domain.finantial;

import com.sun.istack.NotNull;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
public class Money implements ValueObject {
    @Enumerated(value = EnumType.STRING)
    private final Currency currency;
    private final double amount;

    protected Money(){
        this.currency = Currency.MKD;
        this.amount = 0.0;
    }

    public Money(@NotNull Currency currency, @NotNull double amount){
        this.currency = currency;
        this.amount = amount;
    }

    public static Money valueOf(Currency currency, double amount){
        return new Money(currency, amount);
    }

    public Money add(Money money){
        if(!currency.equals(money.currency)){
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency, amount + money.amount);
    }

    public Money subtract(Money money){
        if(!currency.equals(money.currency)){
            throw new IllegalArgumentException("Cannot subtract two Money objects with different currencies");
        }
        return new Money(currency, amount - money.amount);
    }

    public Money multiply(double m){
        return new Money(currency, amount * m);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount && currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }







}

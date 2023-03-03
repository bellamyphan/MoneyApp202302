package objects.amount;

import application.SystemConfiguration;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class AmountObject {
    private BigDecimal amount;

    public AmountObject(BigDecimal bigDecimal) {
        amount = bigDecimal;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void add(AmountObject amountObject) {
        amount = amount.add(amountObject.getAmount());
    }

    @Override
    public String toString() {
        return NumberFormat.getCurrencyInstance(SystemConfiguration.locale).format(amount);
    }
}

package objects.amount;

import application.SystemConfiguration;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class AmountObject {
    private final Locale locale;
    private final BigDecimal amount;

    public AmountObject(BigDecimal bigDecimal) {
        locale = SystemConfiguration.locale;
        this.amount = bigDecimal;
    }

//    public AmountObject(Locale locale, BigDecimal bigDecimal) {
//        this.locale = locale;
//        this.amount = bigDecimal;
//    }

    @Override
    public String toString() {
        return NumberFormat.getCurrencyInstance(locale).format(amount);
    }
}

package objects.bank;

import java.math.BigDecimal;
import java.util.Date;

public record BankObject(String bankName, String website, String accountName, Date openDate, Date closeDate,
                         BankType accountType, BigDecimal interestRate) {

    @Override
    public String toString() {
        return bankName + " - " + accountName;
    }
}

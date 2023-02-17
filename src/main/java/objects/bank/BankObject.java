package objects.bank;

import java.util.Date;

public class BankObject {
    private final String bankName;
    private final String website;
    private final String accountName;
    private final Date openDate;
    private final Date closeDate;
    private final BankType accountType;
    private final Double interestRate;

    public BankObject(String bankName, String website, String accountName, Date openDate, Date closeDate,
                      BankType accountType, Double interestRate) {
        this.bankName = bankName;
        this.website = website;
        this.accountName = accountName;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.accountType = accountType;
        this.interestRate = interestRate;
    }

    public String getBankName() {
        return bankName;
    }

    public String getWebsite() {
        return website;
    }

    public String getAccountName() {
        return accountName;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public BankType getAccountType() {
        return accountType;
    }

    public Double getInterestRate() {
        return interestRate;
    }
}

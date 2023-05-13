package objects.bank;

import java.util.ArrayList;
import java.util.List;

public class BankHandler {
    private final List<BankObject> banks;

    public BankHandler(List<BankObject> banks) {
        this.banks = banks;
    }

    public List<BankObject> getBanks() {
        return this.banks;
    }

    public List<BankObject> getBanksExclude(BankObject bankObject) {
        List<BankObject> resultList = new ArrayList<>();
        for (BankObject bank : banks) {
            if (bank.bankName().compareToIgnoreCase(bankObject.bankName()) == 0
                    && bank.accountName().compareToIgnoreCase(bankObject.accountName()) == 0) {
                continue;
            }
            resultList.add(bank);
        }
        return resultList;
    }

    public BankObject getBank(String bankNameAccountName) {
        if (bankNameAccountName.length() > 0) {
            String[] splitStrings = bankNameAccountName.split(" - ");
            String bankName = splitStrings[0];
            String accountName = splitStrings[1];
            if (banks != null) {
                for (BankObject bank : banks) {
                    if (bank.bankName().compareToIgnoreCase(bankName) == 0
                            && bank.accountName().compareToIgnoreCase(accountName) == 0) {
                        return bank;
                    }
                }
            }
        }
        return null;
    }
}

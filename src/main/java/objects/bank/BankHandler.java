package objects.bank;

import dao.bank.BankReaderDao;

import java.util.ArrayList;
import java.util.List;

public class BankHandler {
    private final List<BankObject> banks;

    public BankHandler() {
        banks = new BankReaderDao().getBanks();
    }

    public List<BankObject> getBanks() {
        return banks;
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
            String bankName = bankNameAccountName.split(" - ")[0];
            String accountName = bankNameAccountName.split(" - ")[1];
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

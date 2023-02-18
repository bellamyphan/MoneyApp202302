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
}

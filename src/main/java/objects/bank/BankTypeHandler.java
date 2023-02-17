package objects.bank;

import java.util.Arrays;
import java.util.List;

public class BankTypeHandler {
    private final List<BankType> bankTypeList;

    public BankTypeHandler() {
        bankTypeList = Arrays.asList(BankType.values());
    }

    public List<BankType> getBankTypeList() {
        return bankTypeList;
    }
}

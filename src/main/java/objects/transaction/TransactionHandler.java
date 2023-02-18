package objects.transaction;

import dao.transaction.TransactionReaderDao;

import java.util.List;

public class TransactionHandler {
    private final List<TransactionObject> transactions;

    public TransactionHandler() {
        transactions = new TransactionReaderDao().getTransactions();
    }

    public Integer getAutoGeneratedId() {
        if (transactions == null || transactions.size() == 0)
            return 0;
        else
            return transactions.get(transactions.size() - 1).getId() + 1;
    }
}

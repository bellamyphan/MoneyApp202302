package objects.transaction;

import objects.type.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionHandler {
    private final List<TransactionObject> transactions;

    public TransactionHandler(List<TransactionObject> transactions) {
        this.transactions = transactions;
    }

    public Integer getAutoGeneratedId() {
        if (transactions == null || transactions.size() == 0)
            return 0;
        else
            return transactions.get(transactions.size() - 1).getId() + 1;
    }

    public List<TransactionObject> getTransactionsFilterByType(Type type) {
        List<TransactionObject> transactionList = new ArrayList<>();
        for (TransactionObject transaction : transactions) {
            if (transaction.getType() == type) {
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }

    public List<TransactionObject> getTransactionsFilterByName(String name) {
        List<TransactionObject> transactionList = new ArrayList<>();
        for (TransactionObject transaction : transactions) {
            if (transaction.getName().compareToIgnoreCase(name) == 0) {
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }

    public List<TransactionObject> getTransactionsFilterByTime(Date startDate, Date endDate) {
        List<TransactionObject> transactionList = new ArrayList<>();
        for (TransactionObject transaction : transactions) {
            if (startDate.compareTo(transaction.getDate()) <= 0 && transaction.getDate().compareTo(endDate) <= 0) {
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }
}

package application;

import dao.transaction.TransactionReaderDao;
import objects.report.ReportHandler;
import objects.transaction.TransactionObject;

import java.util.List;

public class Testing1 {
    public static void main(String[] args) {
        List<TransactionObject> transactions = new TransactionReaderDao().getTransactions();
        System.out.println(ReportHandler.getTypeReports(transactions));
    }
}

package objects.report;

import objects.transaction.TransactionObject;

import java.util.List;

public class ReportHandler {
    public static ReportList getTypeReports(List<TransactionObject> transactions) {
        ReportList reportList = new ReportList();
        for (TransactionObject transaction : transactions) {
            reportList.update(transaction);
        }
        return reportList;
    }
}

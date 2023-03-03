package objects.report;

import objects.amount.AmountObject;
import objects.transaction.TransactionObject;
import objects.type.Type;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReportList {
    private final List<ReportObject> reportList;

    public ReportList() {
        reportList = new ArrayList<>();
        for (Type type : Type.values()) {
            reportList.add(new ReportObject(type));
        }
    }

    public void update(TransactionObject transaction) {
        Objects.requireNonNull(getReportObject(transaction.getType())).update(transaction.getAmount());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ReportObject report : reportList) {
            if (report.getAmountObject().getAmount().compareTo(new BigDecimal("0")) != 0) {
                stringBuilder.append(report).append('\n');
            }
        }
        stringBuilder.append("OVERALL BALANCE: ").append(getOverallBalance());
        return stringBuilder.toString();
    }

    private ReportObject getReportObject(Type type) {
        for (ReportObject reportObject : reportList) {
            if (reportObject.getType() == type) {
                return reportObject;
            }
        }
        return null;
    }

    private AmountObject getOverallBalance() {
        AmountObject overallBalance = new AmountObject(new BigDecimal("0"));
        for (ReportObject report : reportList) {
            overallBalance.add(report.getAmountObject());
        }
        return overallBalance;
    }
}

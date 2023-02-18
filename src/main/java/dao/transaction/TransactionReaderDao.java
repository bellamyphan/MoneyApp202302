package dao.transaction;

import application.SystemConfiguration;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import objects.amount.AmountObject;
import objects.bank.BankHandler;
import objects.bank.BankObject;
import objects.location.LocationObject;
import objects.transaction.TransactionObject;
import objects.type.Type;
import tools.BooleanHandler;
import tools.DateHandler;
import tools.DoubleQuoteHandler;
import tools.StringHandler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionReaderDao {

    private final List<TransactionObject> transactions;

    public TransactionReaderDao() {
        transactions = new ArrayList<>();
        File transactionFile = new File(SystemConfiguration.transactionDataPath);
        if (transactionFile.exists()) {
            try (FileReader fileReader = new FileReader(SystemConfiguration.transactionDataPath);
                 CSVReader csvReader = new CSVReader(fileReader)) {
                String[] transactionLine;
                boolean skipHeaderLine = true;
                while ((transactionLine = csvReader.readNext()) != null) {
                    if (skipHeaderLine) {
                        skipHeaderLine = false;
                        continue;
                    }
                    transactions.add(getTransactionFromTransactionLine(transactionLine));
                }
            } catch (IOException | CsvValidationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<TransactionObject> getTransactions() {
        return transactions;
    }

    private TransactionObject getTransactionFromTransactionLine(String[] transactionLine) {
        Integer id = Integer.parseInt(DoubleQuoteHandler.removeDoubleQuote(transactionLine[0]));
        String parentIdString = DoubleQuoteHandler.removeDoubleQuote(transactionLine[1]);
        Integer parentId = parentIdString.length() == 0 ? null : Integer.parseInt(parentIdString);
        Type type = Type.valueOf(DoubleQuoteHandler.removeDoubleQuote(transactionLine[2]));
        Date date = DateHandler.getJavaUtilDate(DoubleQuoteHandler.removeDoubleQuote(transactionLine[3]));
        String currencyString = DoubleQuoteHandler.removeDoubleQuote(transactionLine[4]);
        AmountObject amount =
                new AmountObject(new BigDecimal(StringHandler.getNumberString(currencyString)));
        String note = DoubleQuoteHandler.removeDoubleQuote(transactionLine[5]);
        String name = DoubleQuoteHandler.removeDoubleQuote(transactionLine[6]);
        LocationObject location = new LocationObject(DoubleQuoteHandler.removeDoubleQuote(transactionLine[7]));
        BankObject primaryBank = new BankHandler().getBank(DoubleQuoteHandler.removeDoubleQuote(transactionLine[8]));
        BankObject secondaryBank = new BankHandler().getBank(DoubleQuoteHandler.removeDoubleQuote(transactionLine[9]));
        Boolean isPending = BooleanHandler.getBooleanValueFromString(
                DoubleQuoteHandler.removeDoubleQuote(transactionLine[10]));
        return new TransactionObject(id, parentId, type, date, amount, note, name, location,
                primaryBank, secondaryBank, isPending);
    }
}

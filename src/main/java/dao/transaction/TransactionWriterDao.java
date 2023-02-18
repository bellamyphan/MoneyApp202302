package dao.transaction;

import application.SystemConfiguration;
import com.opencsv.CSVWriter;
import objects.transaction.TransactionObject;
import tools.DateHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TransactionWriterDao {
    public TransactionWriterDao() {
        // Write header line if needed
        File transactionFile = new File(SystemConfiguration.transactionDataPath);
        if (!transactionFile.exists() || transactionFile.length() == 0) {
            try (FileWriter fileWriter = new FileWriter(SystemConfiguration.transactionDataPath, false);
                 CSVWriter csvWriter = new CSVWriter(fileWriter)) {
                String[] headerLine = new String[]{"Transaction Id", "Parent Id", "Type", "Date", "Amount", "Note",
                        "Name", "Location", "Primary Bank", "Secondary Bank", "Pending"};
                csvWriter.writeNext(headerLine);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addATransactionToDatabase(TransactionObject transactionObject) {
        try (FileWriter fileWriter = new FileWriter(SystemConfiguration.transactionDataPath, true);
             CSVWriter csvWriter = new CSVWriter(fileWriter)) {
            String[] transactionLine = getTransactionLine(transactionObject);
            csvWriter.writeNext(transactionLine);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] getTransactionLine(TransactionObject transactionObject) {
        String[] transactionLine;
        transactionLine = new String[]{transactionObject.getId().toString(),
                transactionObject.getParentId() != null ? transactionObject.getParentId().toString() : "",
                transactionObject.getType().toString(),
                DateHandler.getDateString(transactionObject.getDate()), transactionObject.getAmount().toString(),
                transactionObject.getNote(), transactionObject.getName(),
                transactionObject.getLocationObject().toString(), transactionObject.getPrimaryBank().toString(),
                transactionObject.getSecondaryBank() != null ? transactionObject.getSecondaryBank().toString() : "",
                transactionObject.getPending().toString()};
        return transactionLine;
    }
}

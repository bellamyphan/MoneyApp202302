package dao.bank;

import application.SystemConfiguration;
import com.opencsv.CSVWriter;
import objects.bank.BankObject;
import tools.DateHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BankWriterDao {
    public BankWriterDao() {
        // Write header line if needed
        File bankFile = new File(SystemConfiguration.bankDataPath);
        if (!bankFile.exists() || bankFile.length() == 0) {
            try (FileWriter fileWriter = new FileWriter(SystemConfiguration.bankDataPath, false);
                 CSVWriter csvWriter = new CSVWriter(fileWriter)) {
                String[] headerLine = new String[]{"Bank Name", "Website", "Account Name", "Open Date",
                        "Close Date", "Account Type", "Interest Rate"};
                csvWriter.writeNext(headerLine);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addABankToDatabase(BankObject bankObject) {
        // Add the bank to database
        try (FileWriter fileWriter = new FileWriter(SystemConfiguration.bankDataPath, true);
             CSVWriter csvWriter = new CSVWriter(fileWriter)) {
            String[] bankLine = getBankLine(bankObject);
            csvWriter.writeNext(bankLine);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] getBankLine(BankObject bankObject) {
        String[] bankLine;
        bankLine = new String[]{bankObject.bankName(), bankObject.website(), bankObject.accountName(),
                DateHandler.getDateString(bankObject.openDate()),
                DateHandler.getDateString(bankObject.closeDate()), bankObject.accountType().toString(),
                bankObject.interestRate().toString()};
        return bankLine;
    }
}

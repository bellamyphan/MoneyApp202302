package dao.bank;

import application.SystemConfiguration;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import objects.bank.BankObject;
import objects.bank.BankType;
import tools.DateHandler;
import tools.DoubleQuoteHandler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankReaderDao {
    private List<BankObject> banks;

    public BankReaderDao() {
        File bankFile = new File(SystemConfiguration.bankDataPath);
        if (bankFile.exists()) {
            banks = new ArrayList<>();
            try (FileReader fileReader = new FileReader(SystemConfiguration.bankDataPath);
                 CSVReader csvReader = new CSVReader(fileReader)) {
                String[] bankLine;
                boolean skipHeaderLine = true;
                while ((bankLine = csvReader.readNext()) != null) {
                    if (skipHeaderLine) {
                        skipHeaderLine = false;
                        continue;
                    }
                    banks.add(getBankFromBankLine(bankLine));
                }
            } catch (IOException | CsvValidationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<BankObject> getBanks() {
        return banks;
    }

    private BankObject getBankFromBankLine(String[] bankLine) {
        String bankName = DoubleQuoteHandler.removeDoubleQuote(bankLine[0]);
        String website = DoubleQuoteHandler.removeDoubleQuote(bankLine[1]);
        String accountName = DoubleQuoteHandler.removeDoubleQuote(bankLine[2]);
        Date openDate = DateHandler.getJavaUtilDateFromYearMonthDayString(
                DoubleQuoteHandler.removeDoubleQuote(bankLine[3]));
        Date closeDate = DateHandler.getJavaUtilDateFromYearMonthDayString(
                DoubleQuoteHandler.removeDoubleQuote(bankLine[4]));
        BankType accountType = BankType.valueOf(DoubleQuoteHandler.removeDoubleQuote(bankLine[5]));
        BigDecimal interestRate = new BigDecimal(DoubleQuoteHandler.removeDoubleQuote(bankLine[6]));
        return new BankObject(bankName, website, accountName, openDate, closeDate, accountType, interestRate);
    }
}

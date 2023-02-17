package tools;

import application.SystemConfiguration;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsaStateHandler {
    public static List<String> getUsaStateCodes() {
        List<String> stateCodes = new ArrayList<>();
        try (FileReader fileReader = new FileReader(SystemConfiguration.usStatesPath);
             CSVReader csvReader = new CSVReader(fileReader)) {
            String[] stateLine;
            boolean skipHeaderLine = true;
            while ((stateLine = csvReader.readNext()) != null) {
                if (skipHeaderLine) {
                    skipHeaderLine = false;
                    continue;
                }
                stateCodes.add(stateLine[2]);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return stateCodes;
    }
}

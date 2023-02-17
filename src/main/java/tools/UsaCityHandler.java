package tools;

import application.SystemConfiguration;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsaCityHandler {
    public static List<String> getUsaCityNames(String stateCode) {
        List<String> cityNames = new ArrayList<>();
        try (FileReader fileReader = new FileReader(SystemConfiguration.usCitiesPath);
             CSVReader csvReader = new CSVReader(fileReader)) {
            String[] cityLine;
            boolean skipHeaderLine = true;
            while ((cityLine = csvReader.readNext()) != null) {
                if (skipHeaderLine) {
                    skipHeaderLine = false;
                    continue;
                }
                if (cityLine[2].compareToIgnoreCase(stateCode) == 0) {
                    cityNames.add(cityLine[1]);
                }
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return cityNames;
    }
}

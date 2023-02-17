package tools.address;

import application.SystemConfiguration;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsaStateHandler {

    private final List<String> stateNames;

    public UsaStateHandler() {
        stateNames = new ArrayList<>();
        try (FileReader fileReader = new FileReader(SystemConfiguration.usStatesPath);
             CSVReader csvReader = new CSVReader(fileReader)) {
            String[] stateLine;
            boolean skipHeaderLine = true;
            while ((stateLine = csvReader.readNext()) != null) {
                if (skipHeaderLine) {
                    skipHeaderLine = false;
                    continue;
                }
                stateNames.add(stateLine[0]);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getStateNames(String searchString) {
        List<String> resultList = new ArrayList<>();
        for (String stateName : stateNames) {
            if (stateName.toLowerCase().contains(searchString.toLowerCase())) {
                resultList.add(stateName);
            }
        }
        return resultList;
    }

    public String getStateName(String searchString) {
        for (String stateName : stateNames) {
            if (stateName.compareToIgnoreCase(searchString) == 0) {
                return stateName;
            }
        }
        return null;
    }
}

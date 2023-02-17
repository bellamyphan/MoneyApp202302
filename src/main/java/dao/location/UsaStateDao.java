package dao.location;

import application.SystemConfiguration;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import objects.location.UsaStateObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsaStateDao {
    private final List<UsaStateObject> states;

    public UsaStateDao() {
        states = new ArrayList<>();
        try (FileReader fileReader = new FileReader(SystemConfiguration.usStatesPath);
             CSVReader csvReader = new CSVReader(fileReader)) {
            String[] stateLine;
            boolean skipHeaderLine = true;
            while ((stateLine = csvReader.readNext()) != null) {
                if (skipHeaderLine) {
                    skipHeaderLine = false;
                    continue;
                }
                states.add(new UsaStateObject(stateLine[0], stateLine[2]));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UsaStateObject> getStates() {
        return states;
    }
}

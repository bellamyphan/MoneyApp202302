package objects.location;

import dao.location.UsaCityDao;

import java.util.ArrayList;
import java.util.List;

public class UsaCityHandler {
    private final List<String> cityNames;

    public UsaCityHandler(String stateCode) {
        cityNames = new UsaCityDao(stateCode).getCityNames();
    }

    public List<String> getCityNames(String cityNameSearch) {
        List<String> resultList = new ArrayList<>();
        for (String cityName : cityNames) {
            if (cityName.toLowerCase().contains(cityNameSearch.toLowerCase())) {
                resultList.add(cityName);
            }
        }
        return resultList;
    }

    public String getCityName(String cityNameSearch) {
        for (String cityName : cityNames) {
            if (cityName.compareToIgnoreCase(cityNameSearch) == 0)
                return cityName;
        }
        return null;
    }
}

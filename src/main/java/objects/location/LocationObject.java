package objects.location;

public class LocationObject {
    private final String city;
    private final UsaStateObject state;

    public LocationObject(String locationString) {
        if (locationString.length() >= 4) {
            String cityName = locationString.substring(0, locationString.length() - 3);
            String stateCode = locationString.substring(locationString.length() - 2);
            state = new UsaStateHandler().getStateUsingStateCode(stateCode);
            city = new UsaCityHandler(state.stateCode()).getCityName(cityName);
        } else {
            city = null;
            state = null;
        }
    }

    public boolean isValid() {
        return state != null && city != null;
    }

    @Override
    public String toString() {
        return city + " " + state.stateCode();
    }
}

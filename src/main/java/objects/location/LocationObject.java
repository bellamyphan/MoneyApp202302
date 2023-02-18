package objects.location;

public class LocationObject {
    private final String city;
    private final UsaStateObject state;

    public LocationObject(String city, UsaStateObject state) {
        this.city = city;
        this.state = state;
    }

    public LocationObject(String locationString) {
        String cityName = locationString.substring(0, locationString.length() - 3);
        String stateCode = locationString.substring(locationString.length() - 2);
        state = new UsaStateHandler().getStateUsingStateCode(stateCode);
        city = new UsaCityHandler(state.stateCode()).getCityName(cityName);
    }

    @Override
    public String toString() {
        return city + " " + state.stateCode();
    }
}

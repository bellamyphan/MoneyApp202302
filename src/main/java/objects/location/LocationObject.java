package objects.location;

public class LocationObject {
    private final String city;
    private final UsaStateObject state;

    public LocationObject(String city, UsaStateObject state) {
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return city + " " + state.stateCode();
    }
}

package objects.location;

public class UsaStateObject {
    private final String stateName;
    private final String stateCode;

    public UsaStateObject(String stateName, String stateCode) {
        this.stateName = stateName;
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    @Override
    public String toString() {
        return stateName;
    }

    public String getStateCode() {
        return stateCode;
    }
}

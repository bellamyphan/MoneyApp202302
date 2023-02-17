package objects.location;

public record UsaStateObject(String stateName, String stateCode) {

    @Override
    public String toString() {
        return stateName;
    }
}

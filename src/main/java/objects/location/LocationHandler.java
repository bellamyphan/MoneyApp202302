package objects.location;

import objects.transaction.TransactionObject;

import java.util.ArrayList;
import java.util.List;

public class LocationHandler {
    public static List<String> getLocationStringList(List<TransactionObject> transactions) {
        List<String> locations = new ArrayList<>();
        for (TransactionObject transaction : transactions) {
            if (!locations.contains(transaction.getLocationObject().toString())) {
                locations.add(transaction.getLocationObject().toString());
            }
        }
        return locations;
    }
}

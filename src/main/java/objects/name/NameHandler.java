package objects.name;

import objects.transaction.TransactionObject;

import java.util.ArrayList;
import java.util.List;

public class NameHandler {
    public static List<String> getNameStringList(List<TransactionObject> transactions) {
        List<String> names = new ArrayList<>();
        for (TransactionObject transaction : transactions) {
            if (!names.contains(transaction.getName())) {
                names.add(transaction.getName());
            }
        }
        return names;
    }
}

package objects.note;

import objects.transaction.TransactionHandler;
import objects.transaction.TransactionObject;
import objects.type.Type;

import java.util.ArrayList;
import java.util.List;

public class NoteHandler {
    public static List<String> getNoteStringList(Type type) {
        List<String> notes = new ArrayList<>();
        List<TransactionObject> transactions = new TransactionHandler().getTransactions(type);
        for (TransactionObject transaction : transactions) {
            if (!notes.contains(transaction.getNote())) {
                notes.add(transaction.getNote());
            }
        }
        return notes;
    }
}

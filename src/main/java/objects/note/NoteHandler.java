package objects.note;

import objects.transaction.TransactionObject;

import java.util.ArrayList;
import java.util.List;

public class NoteHandler {
    public static List<String> getNoteStringList(List<TransactionObject> transactions) {
        List<String> notes = new ArrayList<>();
        for (TransactionObject transaction : transactions) {
            if (!notes.contains(transaction.getNote())) {
                notes.add(transaction.getNote());
            }
        }
        return notes;
    }
}

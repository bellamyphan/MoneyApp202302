package objects.transaction;

import objects.amount.AmountObject;
import objects.bank.BankObject;
import objects.location.LocationObject;
import objects.type.Type;

import java.util.Date;

public class TransactionObject {
    private final Integer id;
    private final Integer parent_id;
    private final Type type;
    private final Date date;
    private final AmountObject amount;
    private final String note;
    private final String name;
    private final LocationObject locationObject;
    private final BankObject primaryBank;
    private final BankObject secondaryBank;
    private final Boolean isPending;

    @Override
    public String toString() {
        return "TransactionObject{" +
                "id=" + id +
                ", parent_id=" + parent_id +
                ", type=" + type +
                ", date=" + date +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                ", name='" + name + '\'' +
                ", locationObject=" + locationObject +
                ", primaryBank=" + primaryBank +
                ", secondaryBank=" + secondaryBank +
                ", isPending=" + isPending +
                '}';
    }

    public TransactionObject(Integer id, Integer parent_id, Type type, Date date, AmountObject amount,
                             String note, String name, LocationObject locationObject, BankObject primaryBank,
                             BankObject secondaryBank, Boolean isPending) {
        this.id = id;
        this.parent_id = parent_id;
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.note = note;
        this.name = name;
        this.locationObject = locationObject;
        this.primaryBank = primaryBank;
        this.secondaryBank = secondaryBank;
        this.isPending = isPending;
    }
}

package objects.transaction;

import objects.amount.AmountObject;
import objects.bank.BankObject;
import objects.location.LocationObject;
import objects.type.Type;
import tools.DateHandler;

import java.util.Date;

public class TransactionObject {
    private final Integer id;
    private final Integer parentId;
    private final Type type;
    private final Date date;
    private final AmountObject amount;
    private final String note;
    private final String name;
    private final LocationObject locationObject;
    private final BankObject primaryBank;
    private final BankObject secondaryBank;
    private final Boolean isPending;

    public TransactionObject(Integer id, Integer parentId, Type type, Date date, AmountObject amount,
                             String note, String name, LocationObject locationObject, BankObject primaryBank,
                             BankObject secondaryBank, Boolean isPending) {
        this.id = id;
        this.parentId = parentId;
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

    public Integer getId() {
        return id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Type getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public AmountObject getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public String getName() {
        return name;
    }

    public LocationObject getLocationObject() {
        return locationObject;
    }

    public BankObject getPrimaryBank() {
        return primaryBank;
    }

    public BankObject getSecondaryBank() {
        return secondaryBank;
    }

    public Boolean getPending() {
        return isPending;
    }

    public String getSimpleString() {
        return "{" + "date=" + DateHandler.getDateString(date) +
                ", type=" + type +
                ", amount=" + amount + '}';
    }

    @Override
    public String toString() {
        return "TransactionObject{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", type=" + type +
                ", date=" + DateHandler.getDateString(date) +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                ", name='" + name + '\'' +
                ", locationObject=" + locationObject +
                ", primaryBank=" + primaryBank +
                ", secondaryBank=" + secondaryBank +
                ", isPending=" + isPending +
                '}';
    }
}

package objects.report;

import objects.amount.AmountObject;
import objects.type.Type;

import java.math.BigDecimal;

public class ReportObject {
    private final Type type;
    private final AmountObject amountObject;

    public ReportObject(Type type) {
        this.type = type;
        amountObject = new AmountObject(new BigDecimal("0"));
    }

    public Type getType() {
        return type;
    }

    public AmountObject getAmountObject() {
        return amountObject;
    }

    public void update(AmountObject amountObject) {
       this.amountObject.add(amountObject);
    }

    @Override
    public String toString() {
        return type.toString() + ": " + amountObject;
    }
}

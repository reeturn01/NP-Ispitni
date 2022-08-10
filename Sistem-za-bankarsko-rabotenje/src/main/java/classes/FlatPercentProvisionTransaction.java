package classes;

import exceptions.NotImplementedException;

public class FlatPercentProvisionTransaction extends Transaction{

    private final int centsPerDollar;

    public FlatPercentProvisionTransaction(long fromId, long toId, String amount, int centsPerDollar) {
        super(fromId, toId, "FlatPercent", amount);
        this.centsPerDollar = centsPerDollar;
    }

    public int getPercent(){
        return centsPerDollar;
    }
}

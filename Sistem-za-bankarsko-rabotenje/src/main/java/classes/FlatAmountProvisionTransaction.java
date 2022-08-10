package classes;

import exceptions.NotImplementedException;

public class FlatAmountProvisionTransaction extends Transaction{
    private final int flatProvisionDollars;
    private final int flatProvisionCents;

    public FlatAmountProvisionTransaction(long fromId, long toId,String amount, String flatProvision) {
        super(fromId, toId, "FlatAmount", amount);

        String[]flatProvisionParts = flatProvision.split("[.$]");
        this.flatProvisionDollars = Integer.parseInt(flatProvisionParts[0]);
        this.flatProvisionCents = Integer.parseInt(flatProvisionParts[1]);
    }

    public String getFlatAmount(){
        return String.format("%d.%2d", flatProvisionDollars, flatProvisionCents);
    }

    @Override
    public String getAmountWithProvision() {
        String []amountParts = this.getAmount().split("[.$]");
        int amountDollars = Integer.parseInt(amountParts[0]);
        int amountCents = Integer.parseInt(amountParts[1]);

        amountCents = (amountCents + flatProvisionCents)%100;
        amountDollars = (amountDollars + flatProvisionDollars) + (amountCents + flatProvisionCents)/100;

        return String.format("%d.2%d$", amountDollars, amountCents);
    }
}

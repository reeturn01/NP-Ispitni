package classes;

public abstract class Transaction {

    private long fromId;
    private long toId;
    private String description;
    private String amount;

    Transaction(long fromId, long toId, String description, String amount){

        this.fromId = fromId;
        this.toId = toId;
        this.description = description;
        this.amount = amount;
    }

    public long getFromId() {
        return fromId;
    }

    public long getToId() {
        return toId;
    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public abstract String getAmountWithProvision();
}

package classes;

public abstract class Transaction {

    private long formId;
    private long toId;
    private String description;
    private String amount;

    Transaction(long formId, long toId, String description, String amount){

        this.formId = formId;
        this.toId = toId;
        this.description = description;
        this.amount = amount;
    }

    public long getFormId() {
        return formId;
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
}

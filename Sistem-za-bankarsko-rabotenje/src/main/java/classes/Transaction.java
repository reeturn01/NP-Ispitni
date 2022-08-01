package classes;

import exceptions.NotImplementedException;

public abstract class Transaction {

    public String getAmount() {
        throw new NotImplementedException();
    }

    public String getDescription() {
        throw new NotImplementedException();
    }
}

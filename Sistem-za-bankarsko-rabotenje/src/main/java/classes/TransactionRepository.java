package classes;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    private final List<Transaction> transactionsList;

    public TransactionRepository() {
        this.transactionsList = new ArrayList<>();
    }

    public TransactionRepository(List<Transaction> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public boolean makeTransaction(Transaction transaction, Account fromAccount, Account toAccount) {
        if (fromAccount.getBalance().compareTo(transaction.getAmountWithProvision()) < 0){
            return false;
        }
//        String []transferAmountParts = transaction.getAmountWithProvision().split("[.$]");
//        int transferAmountDollars = Integer.parseInt(transferAmountParts[0]);
//        int transferAmountCents = Integer.parseInt(transferAmountParts[1]);
        transactionsList.add(transaction);

        fromAccount.withdraw(transaction.getAmountWithProvision());
        fromAccount.deposit(transaction.getAmountWithProvision());

        return true;
    }

}

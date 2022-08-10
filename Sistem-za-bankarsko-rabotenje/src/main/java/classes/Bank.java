package classes;

import exceptions.NotImplementedException;

import java.util.*;

public class Bank {
    private final String name;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public Bank(String name, Account[] accounts) {
        this.name = name;
        this.accountRepository = new AccountRepository(accounts);
        this.transactionRepository = new TransactionRepository();
    }
    public Bank(String name, AccountRepository accountRepository, TransactionRepository transactionRepository){

        this.name = name;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public boolean makeTransaction(Transaction t) {
        Optional<Account> fromAccount = accountRepository.getAccountWithId(t.getFromId());
        Optional<Account> toAccount = accountRepository.getAccountWithId(t.getToId());
        if (fromAccount.isPresent() && toAccount.isPresent()){
            return transactionRepository.makeTransaction(t, fromAccount.get(), toAccount.get());
        }
        return false;
    }

    public String totalProvision() {
        return transactionRepository.getTotalProvision();
    }

    public String totalTransfers() {
        throw new NotImplementedException();
    }

    public Account[] getAccounts() {
        return accountRepository.getAccounts();
    }

    @Override
    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Name: ").append(this.name).append(System.lineSeparator()).append(System.lineSeparator());
////        sb.append(
////                this.accounts.stream()
////                                .map(Account::toString)
////                                .collect(Collectors.joining(
////                                        System.lineSeparator()+System.lineSeparator(),
////                                        "",
////                                        System.lineSeparator()+System.lineSeparator())
////                                )
////        );
//        sb.append("Total provisions: ").append(this.totalProvisions).append(System.lineSeparator());
//        sb.append("Total transfers: ").append(this.totalTransfers).append(System.lineSeparator());
//        return sb.toString();
        throw new NotImplementedException();
    }
}

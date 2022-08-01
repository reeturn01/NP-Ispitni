package classes;

import exceptions.NotImplementedException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bank {
    private final String name;
    private final List<Account> accounts;
    private String totalProvisions;
    private String totalTransfers;

    public Bank() {
        this(null, null);
    }

    public Bank(String name, Account[] accounts) {
        this(name, accounts, "0.00$", "0.00$");
    }
    public Bank(String name, Account[] accounts, String totalProvisions, String totalTransfers){
        this.name = name;
        this.accounts = Arrays.stream(accounts)
                .collect(Collectors.toList());
        this.totalTransfers = totalTransfers;
        this.totalProvisions = totalProvisions;
    }

    public Transaction makeTransaction(Transaction t) {
        throw new NotImplementedException();
    }

    public String totalProvision() {
        return this.totalProvisions;
    }

    public String totalTransfers() {
        return this.totalTransfers;
    }

    public Account[] getAccounts() {
        return this.accounts.toArray(Account[]::new);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.name).append(System.lineSeparator()).append(System.lineSeparator());
        sb.append(
                this.accounts.stream()
                                .map(Account::toString)
                                .collect(Collectors.joining(
                                        System.lineSeparator()+System.lineSeparator(),
                                        "",
                                        System.lineSeparator()+System.lineSeparator())
                                )
        );
        sb.append("Total provisions: ").append(this.totalProvisions).append(System.lineSeparator());
        sb.append("Total transfers: ").append(this.totalTransfers).append(System.lineSeparator());
        return sb.toString();
    }
}

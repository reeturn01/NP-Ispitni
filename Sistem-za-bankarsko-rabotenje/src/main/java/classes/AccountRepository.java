package classes;

import exceptions.NotImplementedException;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class AccountRepository {
    private final Map<Long, Account> accounts;

    public AccountRepository(Account[] accounts) {
        this.accounts = Arrays.stream(accounts)
                .collect(
                        toMap(Account::getId, account -> account, (a1, a2) -> a1));
    }

    public Optional<Account> getAccountWithId(long Id){
        return Optional.ofNullable(accounts.get(Id));
   }

    public Account[] getAccounts() {
        return accounts.values().toArray(Account[]::new);
    }
}

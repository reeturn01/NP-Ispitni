package Unit.tests;

import classes.Account;
import classes.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountRepositoryShould {
    private Account[] accounts;
    private int numberOfAccounts = 5;
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accounts = new Account[numberOfAccounts];
        for (int i = 0; i < numberOfAccounts; i++) {
            accounts[i] = new Account();
        }
        accountRepository = new AccountRepository(accounts);
    }

//    @RepeatedTest(10)
//    void get_Account_with_id_if_present(){
//
//
//        int indexOfExpectedAccount = new Random().nextInt(numberOfAccounts);
//        Account expectedAccount = accounts[indexOfExpectedAccount];
//        long IdOfAccountToGet = expectedAccount.getId();
//
//        Assertions.assertEquals(expectedAccount, accountRepository.getAccountWithId(IdOfAccountToGet));
//    }
//
//    @Test
//    void return_null_for_Id_not_in_repository(){
//        long IdNotPresentInRepository = new Random().longs()
//                .dropWhile(value -> Arrays.stream(accounts)
//                        .noneMatch(account -> account.getId() == value))
//                .findAny()
//                .getAsLong();
//
//        Assertions.assertNull(accountRepository.getAccountWithId(IdNotPresentInRepository));
//    }
    @Test
    void return_all_accounts_in_repository(){
        int accountsSize = 10;
        accounts = new Account[accountsSize];

        for (int i = 0; i < accountsSize; i++) {
            accounts[i] = mock(Account.class);
            when(accounts[i].getId()).thenReturn(Long.valueOf(i));
        }

        accountRepository = new AccountRepository(accounts);

        Assertions.assertArrayEquals(accounts, accountRepository.getAccounts());
    }
}

package Unit.tests;

import classes.Account;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountShould {

    @ParameterizedTest
    @ValueSource(strings = {"0.00$", "11.01$", "5.50$", "999.99$"})
    void getBalance(String balance){
        Account account = new Account(null, balance);

        assertEquals(balance, account.getBalance());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0.01$", "11.01$", "5.50$", "999.99$"})
    void setBalance(String newBalance){
        Account account = new Account();
        account.setBalance(newBalance);

        assertEquals(newBalance, account.getBalance());
    }
}

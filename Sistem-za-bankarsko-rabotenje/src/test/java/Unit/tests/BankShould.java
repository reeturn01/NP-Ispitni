package Unit.tests;

import classes.Account;
import classes.Bank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BankShould {
    @Mock
    Account Marko;
    @Mock
    Account Petar;
    @Mock
    Account Simona;

    Account[] accountsToInject;

    @BeforeEach
    void setUp() {
        accountsToInject = new Account[3];
        accountsToInject[0] = Marko;
        accountsToInject[1] = Petar;
        accountsToInject[2] = Simona;
    }

    private Bank bank;

    @ParameterizedTest
    @ValueSource(strings = {"0.00$", "1.11$", "45.87$", "87987956.13$"})
    void return_total_provision(String provision){
        bank = new Bank(null, new Account[0], provision, null);

        assertEquals(provision, bank.totalProvision());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0.00$", "1.11$", "45.87$", "87987956.13$"})
    void return_total_transfers(String transfers){
        bank = new Bank(null, new Account[0], null, transfers);

        assertEquals(transfers, bank.totalTransfers());
    }

    @Test
    void return_String_representation_of_this_Bank(){
        String bankName = "bank1";
        String bankTotalProvision = "55.55$";
        String bankTotalTransfers = "99.99$";
        bank = new Bank(bankName, accountsToInject, bankTotalProvision, bankTotalTransfers);

        String expected  = "Name: " + bankName + System.lineSeparator()+System.lineSeparator()
                + Arrays.stream(accountsToInject)
                        .map(Account::toString)
                        .collect(Collectors.joining(System.lineSeparator()+System.lineSeparator(),"",System.lineSeparator()+System.lineSeparator()))
                + "Total provisions: "+ bankTotalProvision + System.lineSeparator()
                + "Total transfers: " + bankTotalTransfers + System.lineSeparator();
        assertEquals(expected, bank.toString());
    }
}

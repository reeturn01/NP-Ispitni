package Unit.tests;

import classes.*;
import functions.helper.ToAccountMockArgumentConverter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankShould {
    public static final String BANK_NAME = "bank1";

    private int accountsToInjectSize;
    private Account[] accountsToInject;
    private Bank bank;
    @Mock
    private TransactionRepository transactionRepositoryMock;

    @Mock
    private AccountRepository accountRepositoryMock;


    @BeforeEach
    void setUp() {
        accountsToInjectSize = 10;
        accountsToInject = new Account[accountsToInjectSize];
        for (int i = 0; i < accountsToInjectSize; i++) {
            accountsToInject[i] = new Account();
        }

    }
//
//    private Bank bank;
//
//    @Disabled
//    @ParameterizedTest
//    @ValueSource(strings = {"0.00$", "1.11$", "45.87$", "87987956.13$"})
//    void return_total_provision(String provision){
//
//
//        assertEquals(provision, bank.totalProvision());
//    }
//    @Disabled
//    @ParameterizedTest
//    @ValueSource(strings = {"0.00$", "1.11$", "45.87$", "87987956.13$"})
//    void return_total_transfers(String transfers){
//
//
//        assertEquals(transfers, bank.totalTransfers());
//    }
//
//    @Test
//    void return_String_representation_of_this_Bank(){
//        String bankName = "bank1";
//        String bankTotalProvision = "55.55$";
//        String bankTotalTransfers = "99.99$";
//
//
//        String expected  = "Name: " + bankName + System.lineSeparator()+System.lineSeparator()
//                + Arrays.stream(accountsToInject)
//                        .map(Account::toString)
//                        .collect(Collectors.joining(System.lineSeparator()+System.lineSeparator(),"",System.lineSeparator()+System.lineSeparator()))
//                + "Total provisions: "+ bankTotalProvision + System.lineSeparator()
//                + "Total transfers: " + bankTotalTransfers + System.lineSeparator();
//        assertEquals(expected, bank.toString());
//    }
//
    @DisplayName("makeTransaction() functionalities:")
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    @Nested
    class MakeTransaction{
        @BeforeEach
        void setUp() {
            bank = new Bank(BANK_NAME, accountRepositoryMock, transactionRepositoryMock);
        }

    @ParameterizedTest
        @CsvSource({
                "null, null",
                "mock, null",
                "null, mock"
        })
        void fail_to_make_transaction_if_accounts_involved_in_transaction_are_not_present_in_bank(
                @ConvertWith(ToAccountMockArgumentConverter.class) Optional<Account> fromAccount,
                @ConvertWith(ToAccountMockArgumentConverter.class) Optional<Account> toAccount
        ){
            long fromId = new Random().nextLong();
            lenient().when(accountRepositoryMock.getAccountWithId(fromId)).thenReturn(fromAccount);
            long toId = new Random().nextLong();
            lenient().when(accountRepositoryMock.getAccountWithId(toId)).thenReturn(toAccount);

            Transaction transactionToMake = Mockito.mock(Transaction.class);
            when(transactionToMake.getFromId()).thenReturn(fromId);
            when(transactionToMake.getToId()).thenReturn(toId);

            Assertions.assertFalse(bank.makeTransaction(transactionToMake));

            verifyNoInteractions(transactionRepositoryMock);
        }

        @Test
        void return_false_if_sender_has_insufficient_funds(){
            long fromId = new Random().nextLong();
            lenient().when(accountRepositoryMock.getAccountWithId(fromId)).thenReturn(Optional.of(Mockito.mock(Account.class)));
            long toId = new Random().nextLong();
            lenient().when(accountRepositoryMock.getAccountWithId(toId)).thenReturn(Optional.of(Mockito.mock(Account.class)));

            Transaction transactionToMake = Mockito.mock(Transaction.class);
            when(transactionToMake.getFromId()).thenReturn(fromId);
            when(transactionToMake.getToId()).thenReturn(toId);


        }
    }
    
    @Test
    void return_array_of_all_accounts_in_bank(){
        int expectedArraySize = 10;
        Account[] expectedArray = new Account[expectedArraySize];

        for (int i = 0; i < expectedArraySize; i++) {
            expectedArray[i] = mock(Account.class);
        }

        lenient().when(accountRepositoryMock.getAccounts()).thenReturn(expectedArray);

        bank = new Bank(BANK_NAME, accountRepositoryMock, transactionRepositoryMock);
        Assertions.assertArrayEquals(expectedArray, bank.getAccounts());
        verify(accountRepositoryMock).getAccounts();

    }

}



package classes;

import exceptions.NotImplementedException;

import java.util.Objects;
import java.util.Random;

public class Account {

    String name;
    long id;
    String balanceDollars;
    String balanceCents;
    public static final Character DOLLAR_SIGN = '$';
    public Account() {
        this("", "0.00$");
    }

    public Account(String name, String balance) {
        this.name = name;
        String []balanceParts = balance.split("[.$]");

        this.balanceDollars = balanceParts[0];
        this.balanceCents = balanceParts[1];
        this.id = generateId();
    }

    public long generateId() {
        return new Random().nextLong();
    }

    public long getId() {
        return this.id;
    }

    public String getBalance(){
        return String.format("%s.%s%c", this.balanceDollars, this.balanceCents, DOLLAR_SIGN);
    }

    public String getName(){
        return this.name;
    }
    public void setBalance(String balance){
        String []balanceParts = balance.split("[.$]");
        this.balanceDollars = balanceParts[0];
        this.balanceCents = balanceParts[1];
    }

    @Override
    public String toString() {
        return String.format(
                "Name:%s%nBalance:%s%n",
                this.name, this.getBalance()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Objects.equals(name, account.name) && Objects.equals(balanceDollars, account.balanceDollars) && Objects.equals(balanceCents, account.balanceCents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, balanceDollars, balanceCents);
    }

    public void withdraw(String amount) {
        String [] amountToWithdrawParts = amount.split("[.$]");
        int amountToWithdrawDollars = Integer.parseInt(amountToWithdrawParts[0]);
        int amountToWithdrawCents = Integer.parseInt(amountToWithdrawParts[1]);

        String [] balanceParts = this.getBalance().split("[.$]");
        int balanceDollars = Integer.parseInt(balanceParts[0]);
        int balanceCents = Integer.parseInt(balanceParts[1]);

        if (amountToWithdrawCents > balanceCents){
            balanceCents += 100;
            balanceDollars--;
        }
        balanceCents = balanceCents-amountToWithdrawCents;
        balanceDollars = balanceDollars - amountToWithdrawDollars;

        String newBalance = String.format("%d.%2d$", balanceDollars, balanceCents);
        this.setBalance(newBalance);

    }

    public void deposit(String amount) {
        String [] amountToDepositParts = amount.split("[.$]");
        int amountToDepositDollars = Integer.parseInt(amountToDepositParts[0]);
        int amountToDepositCents = Integer.parseInt(amountToDepositParts[1]);

        String [] balanceParts = this.getBalance().split("[.$]");
        int balanceDollars = Integer.parseInt(balanceParts[0]);
        int balanceCents = Integer.parseInt(balanceParts[1]);

        if (balanceCents+amountToDepositCents >= 100){
            balanceDollars++;
        }
        balanceCents = (balanceCents+amountToDepositCents)%100;
        balanceDollars = balanceDollars + amountToDepositDollars;

        String newBalance = String.format("%d.%2d$", balanceDollars, balanceCents);
        this.setBalance(newBalance);
    }
}

package classes;

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
}

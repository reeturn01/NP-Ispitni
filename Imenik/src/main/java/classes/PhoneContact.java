package classes;

import exceptions.DuplicateNumberException;

import java.util.Set;
import java.util.TreeSet;

public class PhoneContact {
    private String name;
    private Set<String> phoneNumbers;

    public PhoneContact(String name) {
        this.name = name;
        this.phoneNumbers = new TreeSet<>();
    }

    public PhoneContact(String name, Set<String> phoneNumbers) {
        this.name = name;
        this.phoneNumbers = phoneNumbers;
    }

    public String getName() {
        return name;
    }

    public void addPhoneNumber(String phoneNumber) throws DuplicateNumberException {
        if(!phoneNumbers.add(phoneNumber)){
            throw new DuplicateNumberException(phoneNumber);
        }
    }

    public boolean hasPhoneNumberContaining(String digits){
        return phoneNumbers.stream()
                .anyMatch(phoneNumber -> phoneNumber.contains(digits));
    }

    public void print() {
        phoneNumbers.forEach(number -> System.out.printf("%s %s%n", this.name, number));
    }

    public void printPhoneNumberContaining(String number) {
        phoneNumbers.stream()
                .filter(phoneNumber -> phoneNumber.contains(number))
                .forEachOrdered(phoneNumber -> System.out.printf("%s %s%n", name, phoneNumber));
    }
}

package classes;

import exceptions.DuplicateNumberException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PhoneBook {
    private Map<String, PhoneContact> contactsByName;
    private Map<String, PhoneContact> contactsByNumber;

    public PhoneBook() {
        this.contactsByName = new HashMap<>();
        this.contactsByNumber = new HashMap<>();
    }

    public PhoneBook(Map<String, PhoneContact> contactsByName, Map<String, PhoneContact> contactsByNumber) {
        this.contactsByName = contactsByName;
        this.contactsByNumber = contactsByNumber;
    }

    public void addContact(String name, String number) throws DuplicateNumberException {
        if (contactsByNumber.containsKey(number))
            throw new DuplicateNumberException(number);

        contactsByName.putIfAbsent(name, new PhoneContact(name));
        PhoneContact tmp = contactsByName.get(name);
        tmp.addPhoneNumber(number);

        contactsByNumber.put(number, tmp);
    }

    public void contactsByNumber(String number){
        List<PhoneContact> contactsToPrint = contactsByName.values()
                .stream()
                .filter(phoneContact -> phoneContact.hasPhoneNumberContaining(number))
                .sorted(Comparator.comparing(PhoneContact::getName))
                .collect(Collectors.toList());
//                .forEachOrdered(phoneContact -> phoneContact.printPhoneNumberContaining(number));

        if (contactsToPrint.isEmpty())
            System.out.println("NOT FOUND");
        else
            contactsToPrint.forEach(contact -> contact.printPhoneNumberContaining(number));

    }
    public void contactsByName(String name){
        if (contactsByName.containsKey(name))
            contactsByName.get(name).print();
        else{
            System.out.println("NOT FOUND");
        }
    }
}

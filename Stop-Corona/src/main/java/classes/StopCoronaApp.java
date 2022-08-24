package classes;

import exceptions.UserIdAlreadyExistsException;

import java.time.LocalDateTime;
import java.util.*;

public class StopCoronaApp {
    private final Map<String, User> users;
    private final List<CoronaCase> coronaCases;

    public StopCoronaApp() {
        this.users = new HashMap<>();
        this.coronaCases = new ArrayList<>();
    }

    public StopCoronaApp(Map<String, User> users, List<CoronaCase> coronaCases) {
        this.users = users;
        this.coronaCases = coronaCases;
    }

    public void addUser(String name, String id) throws UserIdAlreadyExistsException {
        if (users.containsKey(id)){
            throw new UserIdAlreadyExistsException(id);
        }
        users.put(id, new User(name));
    }

    public void addLocations(String id, List<ILocation> iLocations){
        if (users.containsKey(id)){
            User user = users.get(id);
            iLocations.forEach(user::addLocation);
            updateCoronaCases(user);
        }
    }

    private void updateCoronaCases(User user) {
        coronaCases.forEach(coronaCase -> coronaCase.updateContacts(user));
    }

    public void detectNewCase(String id, LocalDateTime timestamp){
        if (users.containsKey(id)){
            User user = users.get(id);
            coronaCases.add(new CoronaCase(user, timestamp));
        }
    }
    public Map<User, Integer> getDirectContacts(User u){
        Map<User, Integer> directContacts = new HashMap<>();
        for (User user : users.values()) {
            if (!user.equals(u)){
                if (user.wasInDirectContactWithUser(u)){
                    int numberOfDirectContacts = user.numberOfDirectContactsWithUser(u);
                    directContacts.put(user, numberOfDirectContacts);
                }
            }
        }
        return directContacts;
    }
    public Collection<User> getIndirectContacts(User u){
        return new ArrayList<User>();
    }
    public void createReport(){
        for (CoronaCase coronaCase:
             coronaCases) {
            //[user_name] [user_id] [timestamp_detected]
            System.out.println(coronaCase.getUser());
            System.out.println("Direct contacts:");
            getDirectContacts(coronaCase.getUser()).entrySet()
                    .forEach(userIntegerEntry ->
                            System.out.println(userIntegerEntry.getKey().getName()+" 1 "+ userIntegerEntry.getValue())
                            );

        }
    }
}

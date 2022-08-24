package classes;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.lang.Math.*;

public class CoronaCase {
    private final User user;
    private final LocalDateTime timestamp;
    private final Map<User, Integer> directContacts;
    private final Map<User, Integer> indirectContacts;

    public CoronaCase(User user, LocalDateTime timestamp) {
        this.user = user;
        this.timestamp = timestamp;
        this.directContacts = new HashMap<>();
        this.indirectContacts = new HashMap<>();
    }

    public User getUser() {
        return this.user;
    }

    public void updateContacts(User otherUser) {
        updateDirectContacts(otherUser);
        updateIndirectContacts(otherUser);
    }

    private void updateIndirectContacts(User otherUser) {
        for (User:
             ) {
            
        }
    }

    private void updateDirectContacts(User otherUser){
        for (ILocation thisLocation :
                user.getLocations()) {
            for (ILocation otherUserLocation :
                    otherUser.getLocations()) {
                if (euclideanDistanceBetweenLocations(thisLocation, otherUserLocation) <= 2 && timeDifferenceInMinutesBetweenLocations(thisLocation, otherUserLocation) < 5){
                    directContacts.putIfAbsent(otherUser, 0);
                    directContacts.compute(otherUser, (user1, integer) -> integer++);
                }
            }
        }
    }
    private double euclideanDistanceBetweenLocations(ILocation location1, ILocation location2){
        return sqrt((pow(location1.getLongitude(), 2) - pow(location2.getLongitude(), 2))
                + (pow(location1.getLatitude(), 2) - pow(location2.getLatitude(), 2))
                );
    }
    private long timeDifferenceInMinutesBetweenLocations(ILocation localDateTime1, ILocation localDateTime2){
        return abs(ChronoUnit.MINUTES.between(localDateTime1.getTimestamp(), localDateTime2.getTimestamp()));
    }
}

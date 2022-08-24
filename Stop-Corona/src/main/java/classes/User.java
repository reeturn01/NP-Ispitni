package classes;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.*;

public class User {
    private final String name;
    private final Set<ILocation> locations;
    private final List<LocalDateTime> infections;

    public User(String name) {
        this.name = name;
        this.locations = new HashSet<>();
        this.infections = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Set<ILocation> getLocations() {
        return locations;
    }

    public void addLocation(ILocation iLocation) {
        this.locations.add(iLocation);
    }

    public void reportInfected(LocalDateTime timestamp) {
        infections.add(timestamp);
    }

    public boolean wasInDirectContactWithUser(User u) {
        for (ILocation location : locations) {
            if(u.locations.stream()
                    .anyMatch(uLocation -> euclideanDistance(location, uLocation) <= 2 && timeDistanceBetweenLocationsIsLessThan5Minutes(location, uLocation))
            )
                return true;
        }
        return false;

    }

    public double euclideanDistance(ILocation location1, ILocation location2){
        return sqrt(
                (pow(location1.getLongitude(), 2) - pow(location2.getLongitude(), 2))
                + (pow(location1.getLatitude(), 2) - pow(location2.getLatitude(), 2))
        );
    }
    public boolean timeDistanceBetweenLocationsIsLessThan5Minutes(ILocation location1, ILocation location2){
        return ChronoUnit.MINUTES.between(location1.getTimestamp(), location2.getTimestamp()) < 5;
    }

    public int numberOfDirectContactsWithUser(User u) {
        int num = 0;
        for(ILocation location : locations){
            num += (int) u.locations.stream()
                    .filter(uLocation -> euclideanDistance(location, uLocation) <= 2 && timeDistanceBetweenLocationsIsLessThan5Minutes(location, uLocation))
                    .count();

        }
        return num;
    }


}

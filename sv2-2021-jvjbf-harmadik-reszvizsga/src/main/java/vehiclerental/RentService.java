package vehiclerental;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RentService {

    private static final int MAX_RENT_MINUTES = 180;

    private Set<Rentable> rentables;
    private Set<User> users;
    private Map<Rentable, User> actualRenting;

    public RentService() {
        this.rentables = new HashSet<>();
        this.users = new HashSet<>();
        this.actualRenting = new TreeMap<>();
    }

    public void registerUser(User user) {
        if (users.contains(user)) {
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        }
        users.add(user);
    }

    public void addRentable(Rentable rentable) {
        rentables.add(rentable);
    }

    public void rent(User user, Rentable rentable, LocalTime time) {
        rentable.rent(time);
        if (actualRenting.containsKey(rentable)) {
            rentable.closeRent();
            throw new IllegalStateException("Not available rentable!");
        }
        if (user.getBalance() < rentable.calculateSumPrice(MAX_RENT_MINUTES)) {
            throw new IllegalStateException("Insufficient balance!");
        }
        actualRenting.put(rentable, user);

    }

    public void closeRent(Rentable rentable, int minutes) {
        if (actualRenting.containsKey(rentable)) {
            actualRenting.get(rentable).minusBalance(rentable.calculateSumPrice(minutes));
            actualRenting.remove(rentable);
            rentable.closeRent();
        }
    }

    public Set<Rentable> getRentables() {
        return rentables;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Map<Rentable, User> getActualRenting() {
        return actualRenting;
    }
}

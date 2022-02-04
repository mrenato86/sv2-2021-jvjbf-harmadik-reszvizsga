package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ApartmentRental {

    private final List<Apartment> apartments = new ArrayList<>();

    public void addApartment(Apartment apartment) {
        if (apartment != null) {
            apartments.add(apartment);
        }
    }

    public List<Apartment> getApartments() {
        return new ArrayList<>(apartments);
    }

    public List<Apartment> findApartmentByLocation(String location) {
        return apartments.stream()
                .filter(a -> a.getLocation().equalsIgnoreCase(location))
                .collect(Collectors.toList());
    }

    public List<Apartment> findApartmentByExtras(String... extras) {
        return apartments.stream()
                .filter(a -> a.getExtras().containsAll(Arrays.asList(extras)))
                .collect(Collectors.toList());
    }


    public boolean isThereApartmentWithBathroomType(BathRoomType bathRoomType) {
        return apartments.stream()
                .anyMatch(a -> a.getBathRoomType() == bathRoomType);
    }

    public List<Integer> findApartmentsSize() {
        return apartments.stream()
                .map(Apartment::getSize)
                .collect(Collectors.toList());
    }
}

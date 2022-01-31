package vehiclerental;

import java.time.LocalTime;
import java.util.Objects;

public class Car implements Rentable {

    private final String id;
    private final int feePerMinute;
    private LocalTime rentingTime;

    public Car(String id, int feePerMinute) {
        this.id = id;
        this.feePerMinute = feePerMinute;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) minutes * feePerMinute;
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    @Override
    public void rent(LocalTime time) {
        rentingTime = time;
    }

    @Override
    public void closeRent() {
        rentingTime = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return feePerMinute == car.feePerMinute && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, feePerMinute);
    }
}

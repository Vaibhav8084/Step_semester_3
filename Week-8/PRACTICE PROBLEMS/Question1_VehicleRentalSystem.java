import java.util.ArrayList;
import java.util.List;

class RentalCustomer {
    private String name;
    public RentalCustomer(String name) { this.name = name; }
    public String getName() { return name; }
}

abstract class RentalVehicle {
    private String id;
    private Rental booking;

    public RentalVehicle(String id) { this.id = id; }
    public String getId() { return id; }
    public abstract double getDailyRate();
    public boolean isAvailable() { return booking == null; }
    void setBooking(Rental booking) { this.booking = booking; }
}

class Sedan extends RentalVehicle {
    public Sedan(String id) { super(id); }
    public double getDailyRate() { return 50.0; }
}
class SUV extends RentalVehicle {
    public SUV(String id) { super(id); }
    public double getDailyRate() { return 80.0; }
}
class Truck extends RentalVehicle {
    public Truck(String id) { super(id); }
    public double getDailyRate() { return 100.0; }
}

class Rental {
    private RentalCustomer customer;
    private RentalVehicle vehicle;
    private int days;

    public Rental(RentalCustomer customer, RentalVehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
    }
    public RentalCustomer getCustomer() { return customer; }
    public RentalVehicle getVehicle() { return vehicle; }
    public double getCharge() { return vehicle.getDailyRate() * days; }
}

class RentalService {
    private List<Rental> rentals = new ArrayList<Rental>();

    public void rent(RentalCustomer customer, RentalVehicle vehicle, int days) {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getClass().getSimpleName() + " " + vehicle.getId() + " is currently unavailable.");
            return;
        }
        if (days <= 0) {
            System.out.println("Rental duration must be at least one day.");
            return;
        }
        Rental rental = new Rental(customer, vehicle, days);
        rentals.add(rental);
        vehicle.setBooking(rental);
        System.out.printf("%s %s rented successfully by %s. Rental charge: $%.2f.%n",
            vehicle.getClass().getSimpleName(), vehicle.getId(), customer.getName(), rental.getCharge());
    }

    public void returnVehicle(RentalCustomer customer, RentalVehicle vehicle) {
        for (int i = 0; i < rentals.size(); i++) {
            Rental rental = rentals.get(i);
            if (rental.getVehicle() == vehicle && rental.getCustomer() == customer) {
                vehicle.setBooking(null);
                rentals.remove(i);
                System.out.println(vehicle.getId() + " returned by " + customer.getName() + ".");
                return;
            }
        }
        System.out.println("No active rental found for " + vehicle.getId() + " and " + customer.getName() + ".");
    }
}

public class Question1_VehicleRentalSystem {
    public static void main(String[] args) {
        RentalService service = new RentalService();
        RentalCustomer customer1 = new RentalCustomer("Customer 1");
        RentalCustomer customer2 = new RentalCustomer("Customer 2");
        RentalCustomer customer3 = new RentalCustomer("Customer 3");
        RentalVehicle sedan = new Sedan("A");
        RentalVehicle suv = new SUV("B");

        service.rent(customer1, sedan, 3);
        service.rent(customer2, sedan, 2);
        service.returnVehicle(customer1, sedan);
        service.rent(customer3, suv, 5);
    }
}

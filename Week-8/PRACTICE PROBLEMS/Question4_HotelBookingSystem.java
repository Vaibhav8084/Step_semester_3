import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class HotelCustomer {
    private String name;
    public HotelCustomer(String name) { this.name = name; }
    public String getName() { return name; }
}

abstract class HotelRoom {
    private String roomNumber;
    private List<Reservation> reservations = new ArrayList<Reservation>();
    public HotelRoom(String roomNumber) { this.roomNumber = roomNumber; }
    public String getRoomNumber() { return roomNumber; }
    public abstract double getPricePerNight();
    public String getCategory() { return getClass().getSimpleName().replace("Room", ""); }
    public boolean isAvailable(LocalDate start, LocalDate end) {
        for (Reservation reservation : reservations) {
            if (reservation.isActive() && start.isBefore(reservation.getEndDate())
                && end.isAfter(reservation.getStartDate())) return false;
        }
        return true;
    }
    void addReservation(Reservation reservation) { reservations.add(reservation); }
}

class StandardRoom extends HotelRoom {
    public StandardRoom(String number) { super(number); }
    public double getPricePerNight() { return 100.0; }
}
class DeluxeRoom extends HotelRoom {
    public DeluxeRoom(String number) { super(number); }
    public double getPricePerNight() { return 150.0; }
}
class Suite extends HotelRoom {
    public Suite(String number) { super(number); }
    public double getPricePerNight() { return 250.0; }
}

class Reservation {
    private HotelCustomer customer;
    private HotelRoom room;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate cancellationDeadline;
    private boolean active = true;

    public Reservation(HotelCustomer customer, HotelRoom room, LocalDate start, LocalDate end, LocalDate deadline) {
        this.customer = customer; this.room = room; this.startDate = start; this.endDate = end;
        this.cancellationDeadline = deadline;
    }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public boolean isActive() { return active; }
    public double getPrice() {
        long nights = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        return nights * room.getPricePerNight();
    }
    public String cancel(LocalDate today) {
        if (!active) return "Reservation is already cancelled.";
        if (today.isAfter(cancellationDeadline)) return "Cancellation deadline has passed.";
        active = false;
        return "Reservation for " + customer.getName() + ", " + room.getCategory() + " Room "
            + room.getRoomNumber() + " (" + startDate.getMonth().toString().substring(0, 1)
            + startDate.getMonth().toString().substring(1).toLowerCase() + " " + startDate.getDayOfMonth()
            + "-" + endDate.getDayOfMonth() + ") cancelled successfully.";
    }
    public String summary() {
        return customer.getName() + ", " + room.getCategory() + " Room " + room.getRoomNumber()
            + " (" + startDate.getMonth().toString().substring(0, 1)
            + startDate.getMonth().toString().substring(1).toLowerCase() + " " + startDate.getDayOfMonth()
            + "-" + endDate.getDayOfMonth() + ")";
    }
}

class HotelBookingService {
    public boolean checkAvailability(HotelRoom room, LocalDate start, LocalDate end) {
        return room.isAvailable(start, end);
    }
    public Reservation reserve(HotelCustomer customer, HotelRoom room, LocalDate start,
                               LocalDate end, LocalDate cancellationDeadline) {
        if (!end.isAfter(start)) {
            System.out.println("Reservation rejected: end date must be after start date.");
            return null;
        }
        if (!room.isAvailable(start, end)) {
            System.out.println(room.getCategory() + " Room " + room.getRoomNumber()
                + " is not available for the selected dates.");
            return null;
        }
        Reservation reservation = new Reservation(customer, room, start, end, cancellationDeadline);
        room.addReservation(reservation);
        System.out.printf(Locale.US, "Reservation confirmed for %s. Price: $%.2f.%n",
            reservation.summary(), reservation.getPrice());
        return reservation;
    }
}

public class Question4_HotelBookingSystem {
    public static void main(String[] args) {
        HotelBookingService service = new HotelBookingService();
        HotelCustomer a = new HotelCustomer("Customer A");
        HotelCustomer b = new HotelCustomer("Customer B");
        HotelCustomer c = new HotelCustomer("Customer C");
        HotelRoom standard = new StandardRoom("101");
        HotelRoom deluxe = new DeluxeRoom("201");
        LocalDate jan1 = LocalDate.of(2026, 1, 1);
        LocalDate jan5 = LocalDate.of(2026, 1, 5);

        System.out.println("Standard Room 101 is "
            + (service.checkAvailability(standard, jan1, jan5) ? "available" : "not available")
            + " from Jan 1 to Jan 5.");
        Reservation booking = service.reserve(a, standard, jan1, jan5, LocalDate.of(2025, 12, 31));
        service.reserve(b, standard, LocalDate.of(2026, 1, 3), LocalDate.of(2026, 1, 7), LocalDate.of(2026, 1, 1));
        System.out.println(booking.cancel(LocalDate.of(2025, 12, 30)));
        service.reserve(c, deluxe, LocalDate.of(2026, 2, 10), LocalDate.of(2026, 2, 12), LocalDate.of(2026, 2, 9));
    }
}

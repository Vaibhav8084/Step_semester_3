import java.util.*;

class TicketCustomer {
    private String name;
    public TicketCustomer(String name) { this.name = name; }
    public String getName() { return name; }
}

abstract class Seat {
    private String seatId;
    public Seat(String seatId) { this.seatId = seatId; }
    public String getSeatId() { return seatId; }
    public abstract double getPrice();
}

class RegularSeat extends Seat {
    public RegularSeat(String id) { super(id); }
    public double getPrice() { return 150.0; }
}
class PremiumSeat extends Seat {
    public PremiumSeat(String id) { super(id); }
    public double getPrice() { return 250.0; }
}
class ReclinerSeat extends Seat {
    public ReclinerSeat(String id) { super(id); }
    public double getPrice() { return 400.0; }
}

class MovieShow {
    private String showName;
    private boolean started;
    private Map<String, Booking> bookedSeats = new HashMap<String, Booking>();

    public MovieShow(String showName) { this.showName = showName; }
    public String getShowName() { return showName; }
    public boolean hasStarted() { return started; }
    public void startShow() { started = true; }
    public boolean isBooked(String id) { return bookedSeats.containsKey(id); }
    public void reserve(String id, Booking booking) { bookedSeats.put(id, booking); }
    public void release(String id) { bookedSeats.remove(id); }
}

class Booking {
    private TicketCustomer customer;
    private MovieShow show;
    private List<Seat> seats;
    private boolean cancelled;

    public Booking(TicketCustomer customer, MovieShow show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<Seat>(seats);
    }
    public double getTotal() {
        double total = 0;
        for (Seat seat : seats) total += seat.getPrice();
        return total;
    }
    public String getSeatList() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < seats.size(); i++) {
            if (i > 0) result.append(", ");
            result.append(seats.get(i).getSeatId());
        }
        return result.toString();
    }
    public String cancel() {
        if (cancelled) return "Booking is already cancelled.";
        if (show.hasStarted()) return "Cannot cancel: the show has started.";
        for (Seat seat : seats) show.release(seat.getSeatId());
        cancelled = true;
        return customer.getName() + "'s booking cancelled. Seats " + getSeatList() + " released.";
    }
}

public class Question3_CampusPremiereTicketCounter {
    public static Booking book(TicketCustomer customer, MovieShow show, List<Seat> seats) {
        if (seats == null || seats.isEmpty()) {
            System.out.println("Booking rejected: select at least one seat.");
            return null;
        }
        if (seats.size() > 6) {
            System.out.println("Booking rejected: maximum 6 seats per booking.");
            return null;
        }
        Set<String> requested = new HashSet<String>();
        for (Seat seat : seats) {
            if (requested.contains(seat.getSeatId()) || show.isBooked(seat.getSeatId())) {
                System.out.println("Seat " + seat.getSeatId() + " is already booked for this show.");
                return null;
            }
            requested.add(seat.getSeatId());
        }
        Booking booking = new Booking(customer, show, seats);
        for (Seat seat : seats) show.reserve(seat.getSeatId(), booking);
        System.out.printf(Locale.US, "Booking confirmed for %s: %s. Total: ₹%.2f.%n",
            customer.getName(), booking.getSeatList(), booking.getTotal());
        return booking;
    }

    public static void main(String[] args) {
        MovieShow show = new MovieShow("7 PM show");
        TicketCustomer asha = new TicketCustomer("Asha");
        TicketCustomer ravi = new TicketCustomer("Ravi");
        TicketCustomer neha = new TicketCustomer("Neha");

        Booking ashaBooking = book(asha, show, Arrays.<Seat>asList(
            new RegularSeat("A1"), new RegularSeat("A2"), new PremiumSeat("F5")));
        book(ravi, show, Arrays.<Seat>asList(new RegularSeat("A2")));
        book(ravi, show, Arrays.<Seat>asList(new ReclinerSeat("R1")));
        System.out.println(ashaBooking.cancel());
        book(neha, show, Arrays.<Seat>asList(new RegularSeat("A2")));
    }
}

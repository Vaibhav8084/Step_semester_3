public class BookingReceipt {

    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = seatNumbers.clone();
    }

    public String[] getSeatNumbers() {
        return seatNumbers.clone();
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] seats = seatNumbers.clone();
        seats[index] = newSeat;
        return new BookingReceipt(bookingId, seats);
    }

    public static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed = 0;
        int nullCount = 0;
        int group = 0;
        int individual = 0;

        for (BookingReceipt receipt : receipts) {
            if (receipt == null) {
                nullCount++;
                continue;
            }

            processed++;

            if (receipt instanceof GroupBookingReceipt) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | " +
               nullCount + " null skipped | " +
               group + " group | " +
               individual + " individual";
    }

    public static void main(String[] args) {
        BookingReceipt r1 = new BookingReceipt("B100", new String[] {"A1", "A2"});
        GroupBookingReceipt r2 = new GroupBookingReceipt("B101", new String[] {"B1", "B2", "B3"}, 3);
        BookingReceipt r3 = r1.withUpdatedSeat(1, "A5");

        BookingReceipt[] receipts = { r1, r2, r3, null };

        System.out.println(processNightlySettlement(receipts));
    }
}

class GroupBookingReceipt extends BookingReceipt {

    private final int groupSize;

    public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }
}

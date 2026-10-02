abstract class DeliveryNote {
    public DeliveryNote() {
    }

    public abstract String confirmDelivery();

    public String confirmDelivery(String signature) {
        return confirmDelivery() + ", signed by " + signature;
    }
}

class ParcelNote extends DeliveryNote {
    private String trackingId;

    public ParcelNote(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {
        return "Parcel " + trackingId + " delivered";
    }
}

class LetterNote extends DeliveryNote {
    private String trackingId;

    public LetterNote(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {
        return "Letter " + trackingId + " delivered";
    }
}

public class Problem5_PackageDropOffLog {
    public static void logAll(DeliveryNote[] notes) {
        for (int i = 0; i < notes.length; i++) {
            System.out.println(notes[i].confirmDelivery());
        }
    }

    public static void main(String[] args) {
        ParcelNote parcel = new ParcelNote("TRK-1");
        System.out.println(parcel.confirmDelivery());
        System.out.println(parcel.confirmDelivery("J. Smith"));

        DeliveryNote[] notes = {parcel, new LetterNote("TRK-2")};
        logAll(notes);
    }
}

interface Printable {
    String printLabel();
}

class PackageBox implements Printable {
    private String trackingId;

    public PackageBox(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String printLabel() {
        return "Package label: " + trackingId;
    }
}

class Invoice implements Printable {
    private String invoiceNumber;

    public Invoice(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String printLabel() {
        return "Invoice label: " + invoiceNumber;
    }
}

public class Problem2_WarehouseLabelPrinter {
    public static void printAll(Printable[] items) {
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i].printLabel());
        }
    }

    public static void main(String[] args) {
        Printable[] items = {
            new PackageBox("TRK-88"),
            new Invoice("INV-42")
        };
        printAll(items);
    }
}

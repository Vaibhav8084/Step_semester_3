import java.util.ArrayList;
import java.util.List;

class ShopCustomer {
    private String name;
    public ShopCustomer(String name) { this.name = name; }
    public String getName() { return name; }
}

class Product {
    private String name;
    private double price;
    public Product(String name, double price) { this.name = name; this.price = price; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}

class OrderItem {
    private Product product;
    private int quantity;
    public OrderItem(Product product, int quantity) { this.product = product; this.quantity = quantity; }
    public double getTotal() { return product.getPrice() * quantity; }
}

interface PaymentMethod {
    String getName();
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public String getName() { return "Credit Card"; }
    public boolean processPayment(double amount) { return true; }
}
class PayPalPayment implements PaymentMethod {
    public String getName() { return "PayPal"; }
    public boolean processPayment(double amount) { return false; }
}
class BankTransferPayment implements PaymentMethod {
    public String getName() { return "Bank Transfer"; }
    public boolean processPayment(double amount) { return true; }
}

class ShopOrder {
    private String orderId;
    private ShopCustomer customer;
    private List<OrderItem> items = new ArrayList<OrderItem>();
    private String status = "Pending";

    public ShopOrder(String orderId, ShopCustomer customer) {
        this.orderId = orderId; this.customer = customer;
    }
    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive.");
        items.add(new OrderItem(product, quantity));
    }
    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) total += item.getTotal();
        return total;
    }
    public String getStatus() { return status; }
    public String getOrderId() { return orderId; }
    public String pay(PaymentMethod method) {
        if (items.isEmpty()) return "Cannot process payment for an empty order.";
        if (status.equals("Paid")) return "Order " + orderId + " is already Paid.";
        System.out.println("Payment initiated via " + method.getName() + " for Order " + orderId + ".");
        if (method.processPayment(getTotal())) {
            status = "Paid";
            return "Payment for Order " + orderId + " successful. Order status: " + status + ".";
        }
        return "Payment for Order " + orderId + " failed. Order status: " + status + ".";
    }
}

public class Question5_ShoppingPaymentProcessing {
    public static void main(String[] args) {
        ShopCustomer x = new ShopCustomer("Customer X");
        ShopOrder orderX = new ShopOrder("X", x);
        System.out.println("Order created for Customer X.");
        orderX.addProduct(new Product("Product A", 20.0), 2);
        orderX.addProduct(new Product("Product B", 15.0), 1);
        System.out.println(orderX.pay(new CreditCardPayment()));

        ShopOrder emptyOrder = new ShopOrder("Y", new ShopCustomer("Customer Y"));
        System.out.println(emptyOrder.pay(new CreditCardPayment()));

        ShopOrder orderZ = new ShopOrder("Z", new ShopCustomer("Customer Z"));
        System.out.println("Order created for Customer Z.");
        orderZ.addProduct(new Product("Product C", 30.0), 1);
        System.out.println(orderZ.pay(new PayPalPayment()));
    }
}

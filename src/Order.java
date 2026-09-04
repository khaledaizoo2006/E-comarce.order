
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;
    private String customerName;
    private List<CartItem> items;
    private double total;
    private OrderStatus status;

    public Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.total = 0.0;
        this.status = OrderStatus.PENDING;
    }

    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public List<CartItem> getItems() { return items; }
    public double getTotal() { return total; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public void calculateTotal() {
        double sum = 0;
        for (CartItem item : items) {
            sum += item.calculateSubtotal();
        }
        this.total = sum;
    }

    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                calculateTotal();
                return;
            }
        }
        items.add(new CartItem(product, quantity));
        calculateTotal();
    }

    public void removeItem(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
        calculateTotal();
    }

    public void displayOrder() {
        System.out.println("Order ID: " + orderId + " | Customer: " + customerName + " | Status: " + status);
        for (CartItem item : items) {
            System.out.println("  - " + item);
        }
        System.out.println("  Total: " + total);
    }


}

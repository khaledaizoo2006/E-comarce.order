
import java.util.*;
public class Store {



    private List<Product> productList = new ArrayList<>();
    private Map<Integer, Product> productMap = new HashMap<>();

    private Map<Integer, Order> allOrdersMap = new HashMap<>();

    private Set<String> categories = new HashSet<>();

    private Queue<Order> shippingQueue = new LinkedList<>();

    private List<Order> deliveredOrders = new ArrayList<>();

    private List<Review> reviews = new ArrayList<>();

    private void deleteProductEverywhere(int id) {
        Product p = productMap.remove(id);
        if (p != null) {
            productList.removeIf(prod -> prod.getId() == id);
        }
    }

    public boolean addProduct(int id, String name, double price, String category, int stock) {
        if (productMap.containsKey(id)) return false;
        Product p = new Product(id, name, price, category, stock);
        productList.add(p);
        productMap.put(id, p);
        categories.add(category);
        return true;
    }

    public void removeProduct(int id) {
        deleteProductEverywhere(id);
    }

    public void displayAllProducts() {
        if (productList.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        for (Product p : productList) {
            System.out.println(p);
        }
    }

    public Product searchProductById(int id) {
        return productMap.get(id);
    }

    public void showAllCategories() {
        System.out.println("Categories: " + categories);
    }

    public void displayProductsOrderedByPrice() {
        List<Product> copy = new ArrayList<>(productList);
        Collections.sort(copy);
        for (Product p : copy) {
            System.out.println(p);
        }
    }

    public boolean createOrder(int orderId, String customerName) {
        if (allOrdersMap.containsKey(orderId)) return false;
        Order order = new Order(orderId, customerName);
        allOrdersMap.put(orderId, order);
        return true;
    }

    public void addItemToOrder(int orderId, int productId, int quantity) {
        Order order = allOrdersMap.get(orderId);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        if (order.getStatus() != OrderStatus.PENDING) {
            System.out.println("Cannot modify order. It is already processed.");
            return;
        }
        Product product = productMap.get(productId);
        if (product == null) {
            System.out.println("Product does not exist.");
            return;
        }
        order.addItem(product, quantity);
        System.out.println("Item added successfully.");
    }

    public void removeItemFromOrder(int orderId, int productId) {
        Order order = allOrdersMap.get(orderId);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        if (order.getStatus() != OrderStatus.PENDING) {
            System.out.println("Cannot modify order. It is already processed.");
            return;
        }
        order.removeItem(productId);
        System.out.println("Item removed successfully.");
    }

    public void displayOrder(int orderId) {
        Order order = allOrdersMap.get(orderId);
        if (order != null) {
            order.displayOrder();
        } else {
            System.out.println("Order not found.");
        }
    }

    public void addOrderToShippingList(int orderId) {
        Order order = allOrdersMap.get(orderId);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        if (order.getItems().isEmpty()) {
            System.out.println("An order with no items cannot be placed in the shipping list.");
            return;
        }
        if (shippingQueue.contains(order)) {
            System.out.println("Order is already in the shipping list.");
            return;
        }
        order.setStatus(OrderStatus.SHIPPED);
        shippingQueue.add(order);
        System.out.println("Order added to shipping list.");
    }

    public void shipNextOrder() {
        if (shippingQueue.isEmpty()) {
            System.out.println("Shipping list is empty.");
            return;
        }
        Order order = shippingQueue.peek();
        if (order.getItems().isEmpty()) {
            System.out.println("This order has no items and cannot be shipped");
            return;
        }
        shippingQueue.poll();
        order.setStatus(OrderStatus.DELIVERED);
        deliveredOrders.add(order);
        System.out.println("Shipped order ID " + order.getOrderId() + " successfully.");
    }

    public void cancelOrder(int orderId) {
        Order order = allOrdersMap.get(orderId);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        if (order.getStatus() == OrderStatus.DELIVERED || order.getStatus() == OrderStatus.CANCELLED) {
            System.out.println("Cannot cancel an already delivered or cancelled order.");
            return;
        }
        if (order.getStatus() == OrderStatus.SHIPPED) {
            shippingQueue.remove(order);
        }
        order.setStatus(OrderStatus.CANCELLED);
        System.out.println("Order cancelled successfully.");
    }

    public Order searchOrderById(int orderId) {
        return allOrdersMap.get(orderId);
    }

    public void addReview(int productId, String customerName, String comment) {
        if (!productMap.containsKey(productId)) {
            System.out.println("Product does not exist.");
            return;
        }
        reviews.add(new Review(productId, customerName, comment));
        System.out.println("Review added.");
    }

    public void showReviewsForProduct(int productId) {
        for (Review r : reviews) {
            if (r.getProductId() == productId) {
                System.out.println(r);
            }
        }
    }

    public void removeOutOfStockProducts() {
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getStockQuantity() == 0) {
                productMap.remove(p.getId());
                iterator.remove();
            }
        }
        System.out.println("Out-of-stock products removed.");
    }

    public void displayOrdersOrderedByTotal() {
        List<Order> copy = new ArrayList<>(allOrdersMap.values());
        copy.sort(new OrderTotalComparator());
        for (Order o : copy) {
            o.displayOrder();
        }
    }

















}
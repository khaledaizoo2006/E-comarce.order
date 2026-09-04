

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- E-Commerce Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Display All Products");
            System.out.println("4. Search Product by ID");
            System.out.println("5. Show All Categories");
            System.out.println("6. Display Products Ordered by Price");
            System.out.println("7. Create Order");
            System.out.println("8. Add Item to Order");
            System.out.println("9. Remove Item from Order");
            System.out.println("10. Display Order");
            System.out.println("11. Add Order to the Shipping List");
            System.out.println("12. Ship Next Order");
            System.out.println("13. Cancel Order");
            System.out.println("14. Search Order by ID");
            System.out.println("15. Add Review to a Product");
            System.out.println("16. Show All Reviews for a Product");
            System.out.println("17. Remove Out-of-Stock Products");
            System.out.println("18. Display Orders Ordered by Total");
            System.out.println("19. Exit");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int pid = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Category: ");
                    String cat = scanner.nextLine();
                    System.out.print("Enter Stock: ");
                    int stock = scanner.nextInt();
                    if (store.addProduct(pid, name, price, cat, stock)) {
                        System.out.println("Product added successfully.");
                    } else {
                        System.out.println("Product ID already exists.");
                    }
                    break;
                case 2:
                    System.out.print("Enter Product ID to remove: ");
                    store.removeProduct(scanner.nextInt());
                    break;
                case 3:
                    store.displayAllProducts();
                    break;
                case 4:
                    System.out.print("Enter Product ID: ");
                    Product p = store.searchProductById(scanner.nextInt());
                    System.out.println(p != null ? p : "Product not found.");
                    break;
                case 5:
                    store.showAllCategories();
                    break;
                case 6:
                    store.displayProductsOrderedByPrice();
                    break;
                case 7:
                    System.out.print("Enter Order ID: ");
                    int oid = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String cName = scanner.nextLine();
                    if (store.createOrder(oid, cName)) {
                        System.out.println("Order created.");
                    } else {
                        System.out.println("Order ID already exists.");
                    }
                    break;
                case 8:
                    System.out.print("Enter Order ID: ");
                    int oIdAddItem = scanner.nextInt();
                    System.out.print("Enter Product ID: ");
                    int pIdAddItem = scanner.nextInt();
                    System.out.print("Enter Quantity: ");
                    int qty = scanner.nextInt();
                    store.addItemToOrder(oIdAddItem, pIdAddItem, qty);
                    break;
                case 9:
                    System.out.print("Enter Order ID: ");
                    int oIdRem = scanner.nextInt();
                    System.out.print("Enter Product ID to remove: ");
                    int pIdRem = scanner.nextInt();
                    store.removeItemFromOrder(oIdRem, pIdRem);
                    break;
                case 10:
                    System.out.print("Enter Order ID: ");
                    store.displayOrder(scanner.nextInt());
                    break;
                case 11:
                    System.out.print("Enter Order ID to ship: ");
                    store.addOrderToShippingList(scanner.nextInt());
                    break;
                case 12:
                    store.shipNextOrder();
                    break;
                case 13:
                    System.out.print("Enter Order ID to cancel: ");
                    store.cancelOrder(scanner.nextInt());
                    break;
                case 14:
                    System.out.print("Enter Order ID: ");
                    Order ord = store.searchOrderById(scanner.nextInt());
                    if (ord != null) ord.displayOrder();
                    else System.out.println("Order not found.");
                    break;
                case 15:
                    System.out.print("Enter Product ID: ");
                    int revPid = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Customer Name: ");
                    String revUser = scanner.nextLine();
                    System.out.print("Comment: ");
                    String comment = scanner.nextLine();
                    store.addReview(revPid, revUser, comment);
                    break;
                case 16:
                    System.out.print("Enter Product ID: ");
                    store.showReviewsForProduct(scanner.nextInt());
                    break;
                case 17:
                    store.removeOutOfStockProducts();
                    break;
                case 18:
                    store.displayOrdersOrderedByTotal();
                    break;
                case 19:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 19);

        scanner.close();
    }
}

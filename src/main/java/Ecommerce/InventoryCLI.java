package Ecommerce;

import java.util.Scanner;

public class InventoryCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryService inventoryService = new InventoryService();

        while (true) {
            System.out.println("\n--- Inventory Management System ---");
            System.out.println("1. Add Product");
            System.out.println("2. Update Stock");
            System.out.println("3. View Inventory");
            System.out.println("4. Sell Product");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    inventoryService.addProduct(name, quantity, price);
                    break;

                case 2:
                    System.out.print("Enter product ID: ");
                    Long productId = scanner.nextLong();
                    System.out.print("Enter quantity change (+/-): ");
                    int quantityChange = scanner.nextInt();
                    inventoryService.updateStock(productId, quantityChange);
                    break;

                case 3:
                    inventoryService.listProducts();
                    break;

                case 4:
                    System.out.print("Enter product ID: ");
                    Long sellProductId = scanner.nextLong();
                    System.out.print("Enter quantity to sell: ");
                    int sellQuantity = scanner.nextInt();
                    inventoryService.sellProduct(sellProductId, sellQuantity);
                    break;

                case 5:
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

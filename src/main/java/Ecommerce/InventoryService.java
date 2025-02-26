package Ecommerce;

import java.util.*;
import java.util.concurrent.locks.*;
public class InventoryService {
    private Map<Long, Product> inventory = new HashMap<>();
    private final Lock lock = new ReentrantLock();
    private long nextId = 1L;
    public void addProduct(String name, int quantity, double price) {
        lock.lock();
        try {
            Product product = new Product(nextId++, name, quantity, price);
            inventory.put(product.getId(), product);
            System.out.println(STR."Product added: \{product}");
        }
        finally {
            lock.unlock();
        }
    }
    public void updateStock(long productId, int quantity) {
        Product product = inventory.get(productId);
        if (product != null) {
            int newQuantity = product.getQuantity() + quantity;
            if (newQuantity < 0) {
                System.out.println("Error: Not enough stock.");
                return;
            }
            product.setQuantity(newQuantity);
            System.out.println("Updated stock: " + product);
        } else {
            System.out.println("Product not found.");
        }
    }
    public void listProducts() {
        if (inventory.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product p : inventory.values()) {
                System.out.println(p.getName()+" have "+p.getQuantity());

            }
        }
    }
    public boolean sellProduct(Long productId, int quantity) {
        Product product = inventory.get(productId);
        if (product != null && product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            System.out.println("Sale completed: " + quantity + "x " + product.getName());
            return true;
        }
        System.out.println("Sale failed: Insufficient stock or invalid product.");
        return false;
    }

}

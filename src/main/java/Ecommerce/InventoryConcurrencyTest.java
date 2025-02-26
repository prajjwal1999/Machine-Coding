package Ecommerce;

import java.util.concurrent.*;

public class InventoryConcurrencyTest {
    public static void main(String[] args) {
        InventoryService inventoryService = new InventoryService();
        inventoryService.addProduct("Laptop", 10, 1200.50);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task1 = () -> inventoryService.sellProduct(1L, 5);
        Runnable task2 = () -> inventoryService.sellProduct(1L, 6); // Should fail
        Runnable task3 = () -> inventoryService.updateStock(1L, 3);

        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inventoryService.listProducts(); // Final inventory state
    }
}


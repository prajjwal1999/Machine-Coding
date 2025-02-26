package Ecommerce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private long id;
    private String name;
    private int quantity;
    private double price;
}

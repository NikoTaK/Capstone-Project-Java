package org.example;

public class Trade {
    String side;
    int size;
    double price;
    String productId;

    public Trade(String side, int size, double price, String productId) {
        this.side = side;
        this.size = size;
        this.price = price;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return side + "\t" + size + "\t" + price + "\t" + productId;
    }
}

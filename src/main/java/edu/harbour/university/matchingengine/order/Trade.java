package edu.harbour.university.matchingengine.order;

import edu.harbour.university.matchingengine.Side;

public class Trade {
    private Side side;
    private int size;
    private double price;
    private String productId;

    public Trade(Side side, int size, double price, String productId) {
        this.side = side;
        this.size = size;
        this.price = price;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return side + " " + size + " " + price + " " + productId;
    }
}

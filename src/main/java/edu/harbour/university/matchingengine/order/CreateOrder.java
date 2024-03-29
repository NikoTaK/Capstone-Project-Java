package edu.harbour.university.matchingengine.order;

import edu.harbour.university.matchingengine.Originator;
import edu.harbour.university.matchingengine.Side;

public class CreateOrder extends Order{
    private final Originator originator;
    private final String id;
    private final Side side;
    private final int size;
    private final double price;
    private final String productId;

    public CreateOrder(Originator originator, String id, Side side, int size, double price, String productId) {
        super(originator, id, OrderType.CREATE);
        this.originator = originator;
        this.id = id;
        this.side = side;
        this.size = size;
        this.price = price;
        this.productId = productId;
    }

    public Side getSide() {
        return side;
    }
    public int getSize() {
        return size;
    }
    public double getPrice() {
        return price;
    }
    public String getProductId() {
        return productId;
    }

}

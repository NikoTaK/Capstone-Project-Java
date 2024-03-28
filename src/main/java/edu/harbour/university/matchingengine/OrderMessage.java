package edu.harbour.university.matchingengine;

public class OrderMessage {
    private final Originator originator;
    private final String messageId;
    private final Side side;
    private final int size;
    private final double price;
    private final String productId;

    public OrderMessage(String originator, String messageId, String action, int size, double price, String productId) {
        this.originator = originator;
        this.messageId = messageId;
        this.action = action;
        this.size = size;
        this.price = price;
        this.productId = productId;
    }

    public String getOriginator() {
        return originator;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getAction() {
        return action;
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

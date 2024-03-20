package org.example;

public class Message {
    String originator;
    String messageId;
    String action;
    String side;
    int size;
    double price;
    String productId;

    public Message(String originator, String messageId, String action, String side, int size, double price, String productId) {
        this.originator = originator;
        this.messageId = messageId;
        this.action = action;
        this.side = side;
        this.size = size;
        this.price = price;
        this.productId = productId;
    }

    public boolean isOrder() {
        return action.equals("BUY") || action.equals("SELL");
    }

    public boolean isCancel() {
        return action.equals("CANCEL");
    }
}

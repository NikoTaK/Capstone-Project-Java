package org.example;

public class Message {
    String originator;
    String messageId;
    String action;
    int size;
    double price;
    String productId;

    public Message(String originator, String messageId, String action, int size, double price, String productId) {
        this.originator = originator;
        this.messageId = messageId;
        this.action = action;
        this.size = size;
        this.price = price;
        this.productId = productId;
    }
}

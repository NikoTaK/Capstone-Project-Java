package edu.harbour.university.matchingengine.order;

import edu.harbour.university.matchingengine.Originator;

public abstract class Order {
    private final String id;
    private final Originator originator;
    private final OrderType orderType;

    public Order(Originator originator, String id, OrderType orderType) {
        this.id = id;
        this.originator = originator;
        this.orderType = orderType;
    }

    public String getId() {
        return id;
    }

    public Originator getOriginator() {
        return originator;
    }

    public OrderType getOrderType() {
        return orderType;
    }
}
package edu.harbour.university.matchingengine.order;

import edu.harbour.university.matchingengine.Originator;

public class CancelOrder extends Order{

    public CancelOrder(Originator originator, String id) {
        super(originator, id, OrderType.CANCEL);
    }
}

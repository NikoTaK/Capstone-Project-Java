package edu.harbour.university.matchingengine.input;

import edu.harbour.university.matchingengine.Originator;
import edu.harbour.university.matchingengine.Side;
import edu.harbour.university.matchingengine.order.CancelOrder;
import edu.harbour.university.matchingengine.order.CreateOrder;
import edu.harbour.university.matchingengine.order.Order;
import edu.harbour.university.matchingengine.order.OrderType;

import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public List<Order> parseInput(List<String> input){
        List<Order> orderMessages = new ArrayList<>();
        for (String currentOrder : input) {
            String[] parts = currentOrder.split("\s+");
            if (parts[2].equals("cancel")) {
                Originator originator = Originator.valueOf(parts[0]);
                String id = parts[1];
                orderMessages.add(new CancelOrder(originator, id));
            } else {
                Originator originator = Originator.valueOf(parts[0]);
                String id = parts[1];
                Side side = Side.valueOf(parts[2]);
                int size = Integer.parseInt(parts[3]);
                double price = Double.parseDouble(parts[4]);
                String productId = parts[5];
                orderMessages.add(new CreateOrder(originator, id, side, size, price, productId));
            }
        }
        return orderMessages;
    }
}
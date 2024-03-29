package edu.harbour.university.matchingengine;

import edu.harbour.university.matchingengine.order.CancelOrder;
import edu.harbour.university.matchingengine.order.CreateOrder;
import edu.harbour.university.matchingengine.order.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {
    List<CreateOrder> orderMessages = new ArrayList<>();
    private static final int MAX_POSITION = 5000;
    Position position = new Position(MAX_POSITION);

    public List<Trade> processOrder(CreateOrder order) {
        List<Trade> Trades = new ArrayList<>();
        if (order.getSide().equals(Side.BUY)) {
            Position.updateAvailableQuantity(order.getSize());
            this.orderMessages.add(order);
        } else if (order.getSide().equals(Side.SELL)) {
            for (CreateOrder message : new ArrayList<>(orderMessages)) {
                if (message.getSide().equals(Side.BUY) && message.getProductId().equals(order.getProductId()) && message.getPrice() >= order.getPrice() && position.currentPosition - order.getSize() >= -position.maxPosition && Position.totalAvailableQuantity >= order.getSize()) {
                    int tradeSize = Math.min(order.getSize(), message.getSize());
                    position.updatePosition(tradeSize, message.getSide());
                    Trades.add(new Trade(message.getSide(), tradeSize, order.getPrice(), message.getProductId()));
                    if (message.getSize() - tradeSize == 0) {
                        orderMessages.remove(order);
                    }
                    if (order.getSize() - tradeSize == 0) {
                        break;
                    }
                }
            }
        }
        return Trades;
    }

    public void processCancelOrder(CancelOrder cancelOrder) {
        String orderId = cancelOrder.getId();
        for (int i = 0; i < orderMessages.size(); i++) {
            Order order = orderMessages.get(i);
            if (order.getId().equals(orderId)) {
                orderMessages.remove(i);
                return;
            }
        }

    }
}

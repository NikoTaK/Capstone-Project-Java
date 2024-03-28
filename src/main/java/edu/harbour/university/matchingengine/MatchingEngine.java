package edu.harbour.university.matchingengine;

import edu.harbour.university.matchingengine.input.InputParser;
import edu.harbour.university.matchingengine.input.InputReader;
import edu.harbour.university.matchingengine.order.CancelOrder;
import edu.harbour.university.matchingengine.order.CreateOrder;
import edu.harbour.university.matchingengine.order.Order;

import java.util.ArrayList;
import java.util.List;

public class MatchingEngine {
    private final InputReader inputReader;
    private final InputParser inputParser;

    private final OrderProcessor orderProcessor;

    public MatchingEngine(InputReader inputReader, InputParser inputParser, OrderProcessor orderProcessor) {
        this.inputReader = inputReader;
        this.inputParser = inputParser;
        this.orderProcessor = orderProcessor;
    }

    public List<Trade> run(long maxPosition) {
        List<Trade> trades = new ArrayList<>();
        while (true) {
            String input = inputReader.readOrder();
            Order order = inputParser.parseInput(input);
            switch (order.getOrderType()) {
                case CREATE -> trades.addAll(orderProcessor.processOrder((CreateOrder) order));
                case CANCEL -> orderProcessor.processCancelOrder((CancelOrder) order);
            }

        }
    }
}

package edu.harbour.university.matchingengine;

import edu.harbour.university.matchingengine.input.FileInputReader;
import edu.harbour.university.matchingengine.input.InputParser;
import edu.harbour.university.matchingengine.input.InputReader;
import edu.harbour.university.matchingengine.order.CancelOrder;
import edu.harbour.university.matchingengine.order.CreateOrder;
import edu.harbour.university.matchingengine.order.Order;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static edu.harbour.university.matchingengine.order.OrderType.CANCEL;

public class MatchingEngine {
    private static InputReader inputReader;
    private static InputParser inputParser;

    private static OrderProcessor orderProcessor;

    public MatchingEngine(InputReader inputReader, InputParser inputParser, OrderProcessor orderProcessor) {
        this.inputReader = inputReader;
        this.inputParser = inputParser;
        this.orderProcessor = orderProcessor;
    }

    public static List<Trade> run() throws FileNotFoundException {
        List<Trade> trades = new ArrayList<>();
        List<String> input = inputReader.readOrder();
        List<Order> order = inputParser.parseInput(input);
        for (int i = 0; i < order.size(); i++) {
            switch (order.get(i).getOrderType()) {
                case CREATE -> trades.addAll(orderProcessor.processOrder((CreateOrder) order.get(i)));
                case CANCEL -> orderProcessor.processCancelOrder((CancelOrder) order.get(i));
            }

        }
        return trades;
    }
}
package edu.harbour.university.matchingengine;

import edu.harbour.university.matchingengine.input.ConsoleInputReader;
import edu.harbour.university.matchingengine.input.InputReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int MAX_POSITION = 5000;

    public static void processVenueMessage(OrderMessage orderMessage, Position position, List<OrderMessage> orderOrderMessages) {
        if (orderMessage.action.equals("SELL")) {
            for (OrderMessage order : new ArrayList<>(orderOrderMessages)) {
                if (order.action.equals("BUY") && order.productId.equals(orderMessage.productId) && order.price >= orderMessage.price && position.currentPosition - orderMessage.size >= -position.maxPosition) {
                    int tradeSize = Math.min(order.size, orderMessage.size);
                    System.out.println(new Trade(order.action, tradeSize, orderMessage.price, order.productId));
                    position.updatePosition(tradeSize, order.action);
                    order.size -= tradeSize;
                    orderMessage.size -= tradeSize;
                    if (order.size == 0) {
                        orderOrderMessages.remove(order);
                    }
                    if (orderMessage.size == 0) {
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long maxPosition = scanner.nextLong();
        InputReader inputReader = new ConsoleInputReader(scanner);
        MatchingEngine matchingEngine = new MatchingEngine(inputReader, ...);
        matchingEngine.run(maxPosition);
        new TradePrinter().print(List<Trade> trades);
        try {
            File file = new File("/Users/macbookpro/IdeaProjects/ProjectPilot/src/main/java/org/example/orders");
            Scanner scanner = new Scanner(file);
            Position position = new Position(MAX_POSITION);
            List<OrderMessage> orderOrderMessages = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("FINISH")) {
                    break;
                }

                String[] parts = line.split("\\s+");
                if (parts[0].equals("DF")) {
                    if (parts[2].equals("cancel")) {
                        String messageId = parts[1];
                        orderOrderMessages.removeIf(msg -> msg.messageId.equals(messageId));
                    } else {
                        OrderMessage orderMessage = new OrderMessage(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4]), parts[5]);
                        orderOrderMessages.add(orderMessage);
                    }
                } else if (parts[0].equals("VE")) {
                    OrderMessage orderMessage = new OrderMessage(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4]), parts[5]);
                    processVenueMessage(orderMessage, position, orderOrderMessages);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: orders.txt");
        }
    }
}




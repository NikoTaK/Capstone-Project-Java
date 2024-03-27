package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int MAX_POSITION = 10000;

    public static void processVenueMessage(Message message, Position position, List<Message> orderMessages) {
        if (message.action.equals("SELL")) {
            for (Message order : new ArrayList<>(orderMessages)) {
                if (order.action.equals("BUY") && order.productId.equals(message.productId) && order.price >= message.price) {
                    int tradeSize = Math.min(order.size, message.size);
                    System.out.println(new Trade(order.action, tradeSize, message.price, order.productId));
                    position.updatePosition(tradeSize, order.action);
                    order.size -= tradeSize;
                    message.size -= tradeSize;
                    if (order.size == 0) {
                        orderMessages.remove(order);
                    }
                    if (message.size == 0) {
                        return; // Exit the loop after executing all trades for this venue message
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\ilyad\\IdeaProjects\\matching-engine1\\src\\main\\java\\org\\example\\orders");
            Scanner scanner = new Scanner(file);
            Position position = new Position(MAX_POSITION);
            List<Message> orderMessages = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("FINISH")) {
                    break;
                }

                String[] parts = line.split("\\s+");
                if (parts[0].equals("DF")) {
                    if (parts[2].equals("CANCEL")) {
                        String messageId = parts[1];
                        orderMessages.removeIf(msg -> msg.messageId.equals(messageId));
                    } else {
                        Message message = new Message(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4]), parts[5]);
                        orderMessages.add(message);
                    }
                } else if (parts[0].equals("VE")) {
                    Message message = new Message(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4]), parts[5]);
                    processVenueMessage(message, position, orderMessages);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: orders.txt");
        }
    }
}




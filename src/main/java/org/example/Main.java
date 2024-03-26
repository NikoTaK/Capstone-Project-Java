package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static boolean processVenueMessage(Message message, Position position, List<Message> orderMessages) {
        if (message.action.equals("BUY")) {
            for (Message order : new ArrayList<>(orderMessages)) {
                if (order.action.equals("SELL") && order.productId.equals(message.productId)) {
                    if (position.canAccept(order.size, order.action)) {
                        System.out.println(new Trade(order.action, order.size, order.price, order.productId));
                        position.updatePosition(order.size, order.action);
                        orderMessages.remove(order);
                        return true;
                    }
                }
            }
        } else if (message.action.equals("SELL")) {
            System.out.println(new Trade(message.action, message.size, message.price, message.productId));
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        int maxPosition = 0;
        try {
            File file = new File("C:\\Users\\ilyad\\IdeaProjects\\matching-engine1\\src\\main\\java\\org\\example\\orders");
            Scanner scanner = new Scanner(file);
            Position position = new Position(maxPosition);
            List<Message> orderMessages = new ArrayList<>(6);

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

            for (Message order : orderMessages) {
                System.out.println(new Trade(order.action, order.size, order.price, order.productId));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: orders");
        }
    }
}

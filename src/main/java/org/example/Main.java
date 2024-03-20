package org.example;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main maximum-position=<maximum-position>");
            return;
        }

        int maxPosition = 0;

        try {
            String[] argParts = args[0].split("=");
            maxPosition = Integer.parseInt(argParts[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid maximum position. Please provide an integer value.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Position position = new Position(maxPosition);
        List<Message> orderMessages = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("FINISH")) {
                break;
            }

            String[] parts = line.split("\t");
            if (parts[0].equals("DF")) {
                if (parts[2].equals("CANCEL")) {
                    String messageId = parts[1];
                    orderMessages.removeIf(msg -> msg.messageId.equals(messageId));
                } else {
                    Message message = new Message(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), Double.parseDouble(parts[5]), parts[6]);
                    orderMessages.add(message);
                }
            } else if (parts[0].equals("VE")) {
                Message message = new Message(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), Double.parseDouble(parts[5]), parts[6]);
                processVenueMessage(message, position, orderMessages);
            }
        }

        for (Message order : orderMessages) {
            System.out.println(new Trade(order.side, order.size, order.price, order.productId));
        }
    }

    public static void processVenueMessage(Message message, Position position, List<Message> orderMessages) {
        if (message.side.equals("BUY")) {
            for (Message order : orderMessages) {
                if (order.side.equals("SELL") && order.productId.equals(message.productId)) {
                    if (position.canAccept(order.size, order.side)) {
                        System.out.println(new Trade(order.side, order.size, order.price, order.productId));
                        position.updatePosition(order.size, order.side);
                        orderMessages.remove(order);
                        return;
                    }
                }
            }
        }
    }
}

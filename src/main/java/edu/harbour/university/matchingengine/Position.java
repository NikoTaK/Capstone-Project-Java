package edu.harbour.university.matchingengine;

public class Position {
    int currentPosition;
    int maxPosition;
    static int totalAvailableQuantity;

    public Position(int maxPosition) {
        this.maxPosition = maxPosition;
    }


    public void updatePosition(int size, Side side) {
        if (side.equals("SELL")) {
            currentPosition += size;
        } else {
            currentPosition -= size;
        }
    }

    public static void updateAvailableQuantity(int size) {
        if (size > 0) {
            totalAvailableQuantity += size;
        } else {
            totalAvailableQuantity -= size;
        }
    }

}
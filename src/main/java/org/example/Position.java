package org.example;

public class Position {
    int currentPosition;
    int maxPosition;

    public Position(int maxPosition) {
        this.maxPosition = maxPosition;
    }

    public boolean canAccept(int size, String side) {
        if (side.equals("SELL")) {
            return currentPosition + size <= maxPosition;
        } else {
            return currentPosition - size >= -maxPosition;
        }
    }

    public void updatePosition(int size, String side) {
        if (side.equals("SELL")) {
            currentPosition += size;
        } else {
            currentPosition -= size;
        }
    }
}


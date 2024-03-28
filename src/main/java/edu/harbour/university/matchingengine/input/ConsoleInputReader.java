package edu.harbour.university.matchingengine.input;

import java.util.Scanner;

public class ConsoleInputReader implements InputReader {

    private final Scanner scanner;

    public ConsoleInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readOrder() {
        return null;
    }
}

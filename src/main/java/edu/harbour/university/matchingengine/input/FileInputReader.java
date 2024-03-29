package edu.harbour.university.matchingengine.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileInputReader implements InputReader{
    String path = "/Users/macbookpro/IdeaProjects/ProjectPilot/src/main/resources/orders";
    public List<String> readOrder() throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        List<String> orderMessages = new ArrayList<>();
        String line = scanner.nextLine();
        while (!line.equals("FINISH")) {
            orderMessages.add(line);
            line = scanner.nextLine();
        }
        return orderMessages;
    }

}

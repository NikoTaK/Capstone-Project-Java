package edu.harbour.university.matchingengine;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTests {

    @Test
    void testMatchingEngineWithValidInput() throws FileNotFoundException {
        String input = "DF ID1 BUY 5000 110.0 VEX123\n" +
                "VE ID01 SELL 5000 100.0 VEX123\n" +
                "DF ID2 BUY 1000 98.0 VEX123\n" +
                "VE ID02 SELL 700 98.0 VEX123\n" +
                "FINISH\n";

        String expectedOutput = "BUY 5000 100.0 VEX123\n";

        InputStream originalInput = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;
        System.setOut(new PrintStream(outputStream));

        Main.main(new String[]{});

        System.setIn(originalInput);
        System.setOut(originalOutput);

        assertEquals(expectedOutput, outputStream.toString());
    }
}
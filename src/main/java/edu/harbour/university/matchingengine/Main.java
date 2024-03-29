package edu.harbour.university.matchingengine;

import edu.harbour.university.matchingengine.input.FileInputReader;
import edu.harbour.university.matchingengine.input.InputParser;
import edu.harbour.university.matchingengine.input.InputReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) throws FileNotFoundException {
            InputReader inputReader = new FileInputReader();
            InputParser inputParser = new InputParser();
            OrderProcessor orderProcessor = new OrderProcessor();
            MatchingEngine matchingEngine = new MatchingEngine(inputReader, inputParser, orderProcessor);
            List<Trade> output = matchingEngine.run();
            for (Trade trade : output) {
                Logger.logOutput(String.valueOf(trade));
                System.out.println(trade);
            }
        }
}


package edu.harbour.university.matchingengine.input;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public interface InputReader {
    List<String> readOrder() throws FileNotFoundException;
}
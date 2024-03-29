package edu.harbour.university.matchingengine;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static final String LOG_FILE = "logTradeFirm.txt";

    public static void log(String message) {
        try {
            FileWriter writer = new FileWriter(LOG_FILE, true);
            writer.write(message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package edu.harbour.university.matchingengine;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FILE = "trades.log";

    public static void logTrade(String trade) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            String formattedTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String tradeInfo = formattedTimestamp + " - " + trade.toString();
            writer.write(tradeInfo + "\n");
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }

    public static void logOutput(String output) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            String formattedTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String outputInfo = formattedTimestamp + " - " + output;
            writer.write("Trades:\n" + outputInfo + "\n");
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }
}
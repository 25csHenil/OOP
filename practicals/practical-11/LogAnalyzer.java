import java.io.BufferedReader;
import java.nio.file.*;

public class LogAnalyzer {
    public static void main(String[] args) throws Exception {
        Path logFile = Paths.get("server_sample.log");
        Files.writeString(logFile, "INFO App started\nERROR DB timeout\nINFO User login\nERROR NullPointer\n");

        long totalLines = Files.lines(logFile).count();
        long errorCount = Files.lines(logFile).filter(l -> l.contains("ERROR")).count();
        long sizeBytes = Files.size(logFile);

        System.out.println("Log File Analysis for: " + logFile.getFileName());
        System.out.println("  Total Lines: " + totalLines);
        System.out.println("  Keyword 'ERROR' Occurrences: " + errorCount);
        System.out.println("  File Size: " + sizeBytes + " bytes");

        Files.deleteIfExists(logFile);
    }
}

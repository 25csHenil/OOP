import java.io.*;
import java.nio.file.*;
import java.util.List;

public class MiniBankP11 {
    public static class Account implements Serializable {
        @Serial private static final long serialVersionUID = 101L;
        private final String accountNumber;
        private final String ownerName;
        private long balance;

        public Account(String accNo, String owner, long balance) {
            this.accountNumber = accNo;
            this.ownerName = owner;
            this.balance = balance;
        }
        public String getAccountNumber() { return accountNumber; }
        public long getBalance() { return balance; }
        @Override public String toString() {
            return accountNumber + " | " + ownerName + " | Rs. " + balance;
        }
    }

    public static class StatePersister {
        public static void save(Account[] accounts, Path file) throws IOException {
            try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(file))) {
                oos.writeObject(accounts);
            }
        }
        public static Account[] load(Path file) throws IOException, ClassNotFoundException {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file))) {
                return (Account[]) ois.readObject();
            }
        }
    }

    public static class TransactionLog {
        public static void append(Path file, String entry) throws IOException {
            Files.write(file, List.of(entry), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
    }

    public static class ReportGenerator {
        public static void generateReport(Path logsDir, Path reportOut) throws IOException {
            long totalDeposits = 0;
            long totalWithdrawals = 0;

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(logsDir, "*.log")) {
                for (Path p : stream) {
                    try (BufferedReader reader = Files.newBufferedReader(p)) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split("\\s+");
                            if (parts.length >= 3) {
                                long amt = Long.parseLong(parts[2]);
                                if ("DEPOSIT".equalsIgnoreCase(parts[0])) totalDeposits += amt;
                                else if ("WITHDRAW".equalsIgnoreCase(parts[0])) totalWithdrawals += amt;
                            }
                        }
                    }
                }
            }

            String summary = String.format("=== END OF DAY AUDIT REPORT ===\nTotal Deposits:    Rs. %d\nTotal Withdrawals: Rs. %d\nNet Liquidity:     Rs. %d\n",
                totalDeposits, totalWithdrawals, (totalDeposits - totalWithdrawals));
            Files.writeString(reportOut, summary);
            System.out.println(summary);
        }
    }

    public static void main(String[] args) throws Exception {
        Path dataFile = Paths.get("test_accounts.dat");
        Path logsDir = Paths.get("test_logs");
        Files.createDirectories(logsDir);
        Path logFile = logsDir.resolve("tx_today.log");
        Path reportFile = Paths.get("test_report.txt");

        Account[] accounts = {
            new Account("AC0001", "Riya", 5000),
            new Account("AC0002", "Henil", 12000)
        };

        System.out.println("Saving accounts to dat file:");
        StatePersister.save(accounts, dataFile);

        Account[] loaded = StatePersister.load(dataFile);
        System.out.println("Accounts reloaded successfully: " + loaded.length + " accounts verified.");

        TransactionLog.append(logFile, "DEPOSIT AC0001 500");
        TransactionLog.append(logFile, "WITHDRAW AC0002 200");

        ReportGenerator.generateReport(logsDir, reportFile);

        // Cleanup test files
        Files.deleteIfExists(dataFile);
        Files.deleteIfExists(logFile);
        Files.deleteIfExists(reportFile);
        Files.deleteIfExists(logsDir);
    }
}

class DatabaseConnection implements AutoCloseable {
    private final String dbName;
    public DatabaseConnection(String dbName) {
        this.dbName = dbName;
        System.out.println("[DB OPEN] Connected to " + dbName);
    }
    public void query(String sql) throws Exception {
        if ("BAD_SQL".equals(sql)) throw new RuntimeException("SQL Syntax Error near token BAD");
        System.out.println("[DB QUERY] " + sql);
    }
    @Override public void close() {
        System.out.println("[DB CLOSE] Connection to " + dbName + " safely released.");
    }
}

public class AutoCloseableDemo {
    public static void main(String[] args) {
        try (DatabaseConnection conn = new DatabaseConnection("MiniBankAuditDB")) {
            conn.query("SELECT * FROM transactions");
            conn.query("BAD_SQL");
        } catch (Exception e) {
            System.out.println("Caught inside caller: " + e.getMessage());
        }
    }
}

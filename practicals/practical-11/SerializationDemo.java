import java.io.*;

class UserSession implements Serializable {
    @Serial private static final long serialVersionUID = 1L;
    private final String username;
    private final String role;
    private transient String activeSessionToken; // Not persisted

    public UserSession(String username, String role, String token) {
        this.username = username;
        this.role = role;
        this.activeSessionToken = token;
    }

    @Override public String toString() {
        return "UserSession[username=" + username + ", role=" + role + ", token=" + activeSessionToken + "]";
    }
}

public class SerializationDemo {
    public static void main(String[] args) throws Exception {
        File file = new File("session.ser");
        UserSession original = new UserSession("Henil", "SYSTEM_ADMIN", "JWT_SECRET_TOKEN_999");
        System.out.println("Original Object: " + original);

        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(original);
        }

        // Deserialize
        UserSession loaded;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            loaded = (UserSession) ois.readObject();
        }
        System.out.println("Deserialized Object: " + loaded);
        System.out.println("Note: activeSessionToken is null because it was marked transient!");
        file.delete();
    }
}

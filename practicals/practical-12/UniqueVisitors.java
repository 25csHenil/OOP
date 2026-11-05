import java.util.*;

public class UniqueVisitors {
    public static void main(String[] args) {
        List<String> logins = List.of("alice", "bob", "henil", "alice", "charlie", "bob", "diana");

        Set<String> uniqueSet = new HashSet<>(logins);
        Set<String> orderedSet = new LinkedHashSet<>(logins);

        System.out.println("Total Logins: " + logins.size());
        System.out.println("Unique Users Count: " + uniqueSet.size());
        System.out.print("First 3 distinct users in order of arrival: ");
        orderedSet.stream().limit(3).forEach(u -> System.out.print(u + " "));
        System.out.println();
    }
}

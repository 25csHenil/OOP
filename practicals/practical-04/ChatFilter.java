public class ChatFilter {
    public static void main(String[] args) {
        String[] logs = {
            "10:05 alice Hello there",
            "10:06 bob Hi alice",
            "MALFORMED_LINE_WITHOUT_PARTS",
            "10:07 charlie hello all"
        };

        String keyword = "hello";
        int matches = 0;
        StringBuilder report = new StringBuilder();

        for (String line : logs) {
            String[] parts = line.split(" ", 3);
            if (parts.length < 3) continue; // skip malformed

            String time = parts[0];
            String user = parts[1];
            String message = parts[2];

            if (message.toLowerCase().contains(keyword.toLowerCase())) {
                matches++;
                report.append(time).append(" ").append(user).append(": ").append(message).append("\n");
            }
        }

        System.out.println("Matches: " + matches);
        System.out.print(report);
    }
}

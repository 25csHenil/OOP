import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateFiller {
    public static void main(String[] args) {
        String template = "Dear {name}, order {id} ships {date}.";
        String[] names = {"name", "id"};
        String[] values = {"Riya", "A07"};

        Pattern pattern = Pattern.compile("\\{(\\w+)\\}");
        Matcher matcher = pattern.matcher(template);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String key = matcher.group(1);
            String replacement = "[?]";
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(key)) {
                    replacement = values[i];
                    break;
                }
            }
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);

        System.out.println("Original: " + template);
        System.out.println("Filled:   " + sb.toString());
    }
}

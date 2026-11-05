import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        String text = "java programming language java stream collections framework java memory model collections";
        Map<String, Integer> freqMap = new HashMap<>();

        for (String word : text.split("\\s+")) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(freqMap.entrySet());
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue())); // descending order

        System.out.println("Word Frequencies (Highest to Lowest):");
        for (Map.Entry<String, Integer> entry : list) {
            System.out.printf("  %-15s : %d times\n", entry.getKey(), entry.getValue());
        }
    }
}

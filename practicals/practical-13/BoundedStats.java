public class BoundedStats {
    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) return 0.0;
        double sum = 0.0;
        for (N v : values) {
            sum += v.doubleValue();
        }
        return sum / values.length;
    }

    public static void main(String[] args) {
        Integer[] ints = {10, 20, 30, 40};
        Double[] doubles = {10.5, 20.5, 30.0};
        Long[] longs = {1000L, 2000L, 3000L};

        System.out.println("Integer average: " + average(ints));
        System.out.println("Double average:  " + average(doubles));
        System.out.println("Long average:    " + average(longs));
    }
}

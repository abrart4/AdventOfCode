import java.io.File;
import java.util.Scanner;



public class Day2 {
    public static void main(String[] args) throws Exception {
        part();
    }

    private static void part() throws Exception {
        long sum = 0;

        Scanner input = new Scanner(new File("my day 2 input.txt"));
        String full = input.nextLine();
        String[] ranges = full.split(",");
        for (String range: ranges) {
            int indexOfDash = range.indexOf("-");
            String minString = range.substring(0, indexOfDash);
            String maxString = range.substring(indexOfDash + 1);
            long min = Long.parseLong(minString);
            long max = Long.parseLong(maxString);
            for (long id = min; id <= max; id ++) {
                if (isInvalid(id)) {
                    sum += id;
                }
            }
        }
        System.out.println("Day 3: " + sum);
    }

    private static boolean isInvalid(long id) {

    }
}

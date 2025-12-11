import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
                    System.out.println("id " + id + " invalid");
                    sum += id;
                }
            }
        }
        System.out.println("Day 3: " + sum);
    }

    private static boolean isInvalid(long id) {
        String[] digitStrings = String.valueOf(id).split("");
        int[] digits = new int[digitStrings.length];
        for (int i = 0; i < digitStrings.length; i ++) {
            digits[i] = Integer.parseInt(digitStrings[i]);
        }
        Map<Integer, Integer> lastSeen = new HashMap<>();
        for (int i = 0; i < digits.length; i ++) {
            int digit = digits[i];
            if (lastSeen.containsKey(digit)) {
                int seenOn = lastSeen.get(digit);
                int difference = i - seenOn;
                boolean allEqual = true;
                if (difference + i <= digits.length) {
                    for (int j = 0; j < difference; j++) {
                        if (digits[j] != digits[i + j]) {
                            allEqual = false;
                        }
                    }
                    if (allEqual) {

                        return true;
                    }
                }
            }
            lastSeen.put(digit, i);
        }
        return false;
    }
}

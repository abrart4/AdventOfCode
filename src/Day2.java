import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class Day2 {
    public static void main(String[] args) throws Exception {
        part1();
    }

    private static void part1() throws Exception {
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
        String[] digitStrings = String.valueOf(id).split("");
        int[] digits = new int[digitStrings.length];
        for (int i = 0; i < digitStrings.length; i ++) {
            digits[i] = Integer.parseInt(digitStrings[i]);
        }
        Map<Integer, Integer> lastSeenMap = new HashMap<>();
        for (int i = 0; i < digits.length; i ++) { // i = 4
            int digit = digits[i]; // 1
            if (lastSeenMap.containsKey(digit)) { // true
                // if (i * 2 >= digits.length) return false;
                boolean allEqual = true;
                int lastAddition = 0;
                for (int j = 0; j < i; j ++) { // 0 1 2 3
                    int fromBeginning = digits[j]; // 1 2 3 4
                    lastAddition = j + i;
                    if (lastAddition >= digits.length) return false;
                    int fromNow = digits[lastAddition]; // 5 6 7 8
                    if (fromBeginning != fromNow) {
                        allEqual = false;
                    }
                }

                if (allEqual && !(lastAddition < digits.length - 1)) {
                    return true;
                }
            }
            lastSeenMap.put(digit, i);
        }
        return false;
        // ID:    12341234
        // Index: 01234567
    }
}

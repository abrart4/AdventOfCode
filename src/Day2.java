import java.io.File;
import java.util.*;



public class Day2 {
    public static void main(String[] args) throws Exception {
        part(1);
        part(2);
    }

    private static void part(int part) throws Exception {
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
                if ((part == 1 && isInvalidPart1(id)) || (part == 2 && isInvalidPart2(id))) {
                    sum += id;
                }
            }
        }
        System.out.println("Day 2 part " + part + ": " + sum);
    }

    private static boolean isInvalidPart1(long id) {
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

    private static boolean isInvalidPart2(long id) {
        String idString = String.valueOf(id);
        for (int i = 0; i < idString.length(); i ++) {
            String seq = idString.substring(0, i + 1);
            // i haven't commented anything else and i want to explain how this works but i don't want to think about it
            String[] splitThing = (idString + "?").split(seq);
            boolean yay = true;
            for (int j = 0; j < splitThing.length - 1; j ++) {
                String character = splitThing[j];
                if (!character.equals("")) yay = false;
            }
            if (!splitThing[splitThing.length - 1].equals("?")) yay = false;
            if (splitThing.length < 3) yay = false;
            if (yay) {
                return true;
            }
        }
        return false;
    }
}

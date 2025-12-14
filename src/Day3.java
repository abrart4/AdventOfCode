import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;



public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        part2();
    }

    private static void part1() throws FileNotFoundException {
        Scanner input = new Scanner(new File("my day 3 input.txt"));
        int totalJoltage = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] digitStrings = line.split("");
            int[] digits = new int[digitStrings.length];
            for (int i = 0; i < digitStrings.length; i ++) {
                digits[i] = Integer.parseInt(digitStrings[i]);
            }
            int bestJoltageSoFar = -1;
            for (int i = 0; i < digits.length; i ++) {
                for (int j = i + 1; j < digits.length; j ++) {
                    int combination = Integer.parseInt(digits[i] + "" + digits[j]);
                    if (combination > bestJoltageSoFar) {
                        bestJoltageSoFar = combination;
                    }
                }
            }
            totalJoltage += bestJoltageSoFar;
        }
        System.out.println("Day 3 part 1: " + totalJoltage);
    }

    private static void part2() throws FileNotFoundException {
        Scanner input = new Scanner(new File("my day 3 input.txt"));
        long totalJoltage = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] digitStrings = line.split("");
            int[] digits = new int[digitStrings.length];
            for (int i = 0; i < digitStrings.length; i ++) {
                digits[i] = Integer.parseInt(digitStrings[i]);
            }
            int indexOfBestOne = 0;
            String stringThing = "";
            for (int i = 12; i > 0; i --) {
                int bestOne = -1;
                for (int j = indexOfBestOne; j < digits.length; j ++) {
                    int digit = digits[j];
                    if (digit > bestOne && digits.length - j >= i) {
                        bestOne = digit;
                        indexOfBestOne = j + 1;
                    }
                }
                stringThing += bestOne;
            }
            totalJoltage += Long.parseLong(stringThing);
        }
        System.out.println("Day 3 part 2: " + totalJoltage);
    }
}

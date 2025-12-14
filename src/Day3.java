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
            Map<Integer, ArrayList<Integer>> numberToIndices = new HashMap<>();
            for (int numberToLookFor = 9; numberToLookFor >= 0; numberToLookFor --) {
                ArrayList<Integer> list = new ArrayList<>();
                numberToIndices.put(numberToLookFor, list);
                for (int i = 0; i < digits.length; i ++) {
                    int digit = digits[i];
                    if (digit == numberToLookFor) {
                        list.add(i);
                    }
                }
            }
            System.out.println(numberToIndices);
        }
        System.out.println("Day 3 part 2: " + totalJoltage);
    }

    /**
     * (inefficiently) sorts the number array into descending order so we can just crab the first 12 numbers
     * inefficient
     */
    private static int[] tascosort(int[] numbers) {
        int[] narray = new int[numbers.length];
        Set<Integer> eliminatedIndices = new HashSet<>();
        for (int i = 0; i < narray.length; i ++) {
            int highestNumberSoFar = Integer.MIN_VALUE;
            int indexOfHighest = -1;
            for (int j = 0; j < numbers.length; j ++) {
                int number = numbers[j];
                if (number > highestNumberSoFar && !eliminatedIndices.contains(j)) {
                    highestNumberSoFar = number;
                    indexOfHighest = j;
                }
            }
            narray[i] = highestNumberSoFar;
            eliminatedIndices.add(indexOfHighest);
        }
        return narray;
    }
}

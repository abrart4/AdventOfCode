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
            long bestJoltageSoFar = -1;
            int count = 0;
            for (int a = 0; a < digitStrings.length; a ++) {
                for (int b = a + 1; b < digitStrings.length; b ++) {
                    for (int c = b + 1; c < digitStrings.length; c ++) {
                        for (int d = c + 1; d < digitStrings.length; d ++) {
                            for (int e = d + 1; e < digitStrings.length; e ++) {
                                for (int f = e + 1; f < digitStrings.length; f ++) {
                                    for (int g = f + 1; g < digitStrings.length; g ++) {
                                        for (int h = g + 1; h < digitStrings.length; h ++) {
                                            for (int i = h + 1; i < digitStrings.length; i ++) {
                                                for (int j = i + 1; j < digitStrings.length; j ++) {
                                                    for (int k = j + 1; k < digitStrings.length; k ++) {
                                                        for (int l = k + 1; l < digitStrings.length; l ++) {
                                                            count ++;
                                                            String comboString =
                                                                digitStrings[a]
                                                                    + digitStrings[b]
                                                                    + digitStrings[c]
                                                                    + digitStrings[d]
                                                                    + digitStrings[e]
                                                                    + digitStrings[f]
                                                                    + digitStrings[g]
                                                                + digitStrings[h]
                                                                + digitStrings[i]
                                                                + digitStrings[j]
                                                                + digitStrings[k]
                                                                + digitStrings[l];
                                                            long comboLong = Long.parseLong(comboString);
                                                            if (comboLong > bestJoltageSoFar) {
                                                                bestJoltageSoFar = comboLong;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(count);
            totalJoltage += bestJoltageSoFar;
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

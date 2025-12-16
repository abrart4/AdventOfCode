import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


//
public class Day5 {
    public static void main(String[] args) throws Exception {
        part2();
    }

    private static void part1() throws Exception {
        Scanner input = new Scanner(new File("my day 5 input.txt"));
        ArrayList<String> rangeStrings = new ArrayList<>();
        boolean foundBlankLine = false;
        int freshAmount = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.equals("")) {
                foundBlankLine = true;
                continue;
            }
            if (!foundBlankLine) {
                rangeStrings.add(line);
            }
            else {
                long value = Long.parseLong(line);
                ArrayList<Long> alreadyFreshValues = new ArrayList<>();
                for (int i = 0; i < rangeStrings.size(); i++) {
                    String rangeString = rangeStrings.get(i);
                    int indexOfDash = rangeString.indexOf("-");
                    String lowerBoundString = rangeString.substring(0, indexOfDash);
                    String upperBoundString = rangeString.substring(indexOfDash + 1);
                    long lowerBound = Long.parseLong(lowerBoundString);
                    long upperBound = Long.parseLong(upperBoundString);
                    if (lowerBound <= value && value <= upperBound && !alreadyFreshValues.contains(value)) {
                        freshAmount++;
                        alreadyFreshValues.add(value);
                    }
                }
            }
        }
        System.out.println("Day 5 part 1: " + freshAmount);
    }

    private static void part2() throws Exception {
        Scanner input = new Scanner(new File("my day 5 input.txt"));
        ArrayList<long[]> distinctRanges = new ArrayList<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.equals("")) {
                break;
            }
            int indexOfDash = line.indexOf("-");
            String lowerBoundString = line.substring(0, indexOfDash);
            String upperBoundString = line.substring(indexOfDash + 1);
            long lowerBound = Long.parseLong(lowerBoundString);
            long upperBound = Long.parseLong(upperBoundString);
            boolean distinct = true;
            for (int i = 0; i < distinctRanges.size(); i ++) {
                long[] distinctRange = distinctRanges.get(i);
                long otherLowerBound = distinctRange[0];
                long otherUpperBound = distinctRange[1];
                /*
                hypotheticals:
                original: 10-20
                new:
                5-5 distinct, unchanged
                5-10 undistinct, change by setting lower bound of original to 5
                5-15 undistinct, change by setting lower bound of original to 5
                5-20 undistinct, change by setting lower bound of original to 5
                5-25 undistinct, change by setting lower bound of original to 5 and upper bound of original to 25
                10-10 undistinct, unchanged
                10-15 undistinct, unchanged
                10-20 undistinct, unchanged
                10-25 undistinct, change by setting upper bound of original to 25
                15-15 undistinct, unchanged
                15-20 undistinct, unchanged
                15-25 undistinct, change by setting upper bound of original to 25
                20-20 undistinct, unchanged
                20-25 undistinct, change by setting upper bound of original to 25
                 */

            }
            if (distinct) {
                distinctRanges.add(new long[] {lowerBound, upperBound});
            }
        }
        {
            String x = "";
            x += "(";
            for (long[] range: distinctRanges) {
                x += range[0];
                x += "-";
                x += range[1];
                x += ", ";
            }
            x += ")";
            System.out.println(x);
        }
        long acceptableValues = 0;
        for (int i = 0; i < distinctRanges.size(); i ++) {
            long[] distinctRange = distinctRanges.get(i);
            long lower = distinctRange[0];
            long upper = distinctRange[1];
            // if 5 to 10 then that's 5 6 7 8 9 10 or 6 values
            // (upper - lower) + 1
            acceptableValues += (upper - lower) + 1;
        }
        System.out.println("Day 5 part 2: " + acceptableValues);
    }
}

import java.io.File;
import java.util.*;


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
            boolean isTheSame = false;
            for (int id = 0; id < 1000; id ++) {
                ArrayList<long[]> original = new ArrayList<>(distinctRanges);
                for (int i = 0; i < distinctRanges.size(); i ++) {
                    long[] distinctRange = distinctRanges.get(i);
                    long otherLowerBound = distinctRange[0];
                    long otherUpperBound = distinctRange[1];

                    if (lowerBound <= otherLowerBound && upperBound >= otherLowerBound && upperBound <= otherUpperBound) {
                        distinct = false;
                        distinctRange[0] = lowerBound;
                    }
                    if (lowerBound >= otherLowerBound && lowerBound <= otherUpperBound) {
                        distinct = false;
                        distinctRange[1] = up;
                    }
                }
                isTheSame = eq(distinctRanges, original);
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

    private static boolean eq(ArrayList<long[]> first, ArrayList<long[]> second) {
        boolean isAllTheSame = true;
        for (int i = 0; i < first.size(); i ++) {
            if (!Arrays.equals(first.get(i), second.get(i))) isAllTheSame = false;
        }
        return isAllTheSame;
    }
}

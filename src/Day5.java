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
            String nextLine = input.nextLine();
            int indexOfDash = nextLine.indexOf("-");
            if (indexOfDash != -1) {
                String lowerBoundString = nextLine.substring(0, indexOfDash);
                String upperBoundString = nextLine.substring(indexOfDash + 1);
                long lowerBound = Long.parseLong(lowerBoundString);
                long upperBound = Long.parseLong(upperBoundString);
                distinctRanges.add(new long[] {lowerBound, upperBound});
            }
        }
        if (false) {
        Set<Long> lk = new HashSet<>();
        for (long[] dr : distinctRanges) {
            for (long h = dr[0]; h <= dr[1]; h++) {
                lk.add(h);
            }
        }
        System.out.println(lk.size());
    }
        else{
            boolean isTheSame = false;
            while (!isTheSame) {
                ArrayList<long[]> original = new ArrayList<>(distinctRanges);
                Set<Integer> badIndices = new HashSet<>();
                for (int current = 0; current < distinctRanges.size(); current++) {
                    if (badIndices.contains(current)) continue;
                    long[] distinctRange = distinctRanges.get(current);
                    long lowerBound = distinctRange[0];
                    long upperBound = distinctRange[1];
                    for (int other = 0; other < distinctRanges.size(); other++) {
                        if (other != current && !badIndices.contains(other)) {
                            long[] otherDistinctRange = distinctRanges.get(other);
                            long otherLowerBound = otherDistinctRange[0];
                            long otherUpperBound = otherDistinctRange[1];

                            // 50-75 with 60-80
                            // result: 50-80
                            if (lowerBound <= otherLowerBound && otherLowerBound <= upperBound && upperBound <= otherUpperBound) {
                                distinctRange[1] = otherUpperBound;
                                badIndices.add(other);
                            }
                            // 50-80 with 45-75
                            if (otherLowerBound <= lowerBound && lowerBound <= otherUpperBound && otherUpperBound <= upperBound) {
                                distinctRange[0] = otherLowerBound;
                                badIndices.add(other);
                            }

                        }
                    }
                }

                isTheSame = eq(original, distinctRanges);
            }
            ArrayList<long[]> onesToRemove = new ArrayList<>();
            for (int i = 0; i < distinctRanges.size(); i++) {
                long[] distinctRange = distinctRanges.get(i);
                long lowerBound = distinctRange[0];
                long upperBound = distinctRange[1];

                for (int j = 0; j < distinctRanges.size(); j++) {
                    if (j != i) {
                        long[] otherDistinctRange = distinctRanges.get(j);
                        long otherLowerBound = otherDistinctRange[0];
                        long otherUpperBound = otherDistinctRange[1];
                        if (lowerBound <= otherLowerBound && otherUpperBound <= upperBound) {
                            onesToRemove.add(otherDistinctRange);
                        }
                    }
                }
            }
            for (int i = 0; i < onesToRemove.size(); i++) {
                long[] oneToRemove = onesToRemove.get(i);
                distinctRanges.remove(oneToRemove);
            }
            {
                String x = "";
                x += "(";
                for (long[] range : distinctRanges) {
                    x += range[0];
                    x += "-";
                    x += range[1];
                    x += ", ";
                }
                x += ")";
                System.out.println(x);
            }
            long acceptableValues = 0;
            for (int i = 0; i < distinctRanges.size(); i++) {
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

    private static boolean eq(ArrayList<long[]> first, ArrayList<long[]> second) {
        int firstSize = first.size();
        int secondSize = second.size();
        if (firstSize != secondSize) return false;
        boolean isAllTheSame = true;
        for (int i = 0; i < firstSize; i ++) {
            if (!Arrays.equals(first.get(i), second.get(i))) isAllTheSame = false;
        }
        return isAllTheSame;
    }
}
